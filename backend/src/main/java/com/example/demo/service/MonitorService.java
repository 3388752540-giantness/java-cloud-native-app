package com.example.demo.service;

import io.fabric8.kubernetes.api.model.metrics.v1beta1.NodeMetrics;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class MonitorService {

    @Autowired
    private StringRedisTemplate redisTemplate; // Spring 提供的 Redis 操作工具

    // 自动识别 K8s 集群内部权限 (In-Cluster Config)
    // 因为 Java 跑在 Pod 里，它会自动去读挂载的 Token，不需要手动配 config 文件
    private final KubernetesClient k8sClient = new KubernetesClientBuilder().build();

    // 【核心定时任务】：每 5000 毫秒 (5秒) 跑一次
    @Scheduled(fixedRate = 5000)
    public void collectK8sMetrics() {
        try {
            // 1. 调用 K8s Metrics API (相当于执行 kubectl top nodes)
            List<NodeMetrics> nodeMetricsList = k8sClient.top().nodes().metrics().getItems();

            // 获取当前时间戳，作为折线图的横坐标
            String now = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"))
             .format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            for (NodeMetrics metric : nodeMetricsList) {
                String nodeName = metric.getMetadata().getName(); // 比如 k8s-master
                
                // 2. 提取数据（单位转换：CPU 转为核数，内存转为 Mi）
                // 简化逻辑：直接获取原始字符串
                String cpu = metric.getUsage().get("cpu").getAmount(); 
                String mem = metric.getUsage().get("memory").getAmount();

                // 3. 构建 Redis 数据
                // 格式：时间|CPU|内存。 例子："14:30:05|120m|1500Mi"
                String dataRecord = now + "|" + cpu + "|" + mem;

                // 4. 存入 Redis 的 List 结构
                // Key 命名规范：metrics:node:k8s-master
                String redisKey = "metrics:node:" + nodeName;
                redisTemplate.opsForList().rightPush(redisKey, dataRecord);

                // 5. 【重要】维持“滑动窗口”
                // 只保留最近的 50 条数据。防止 Redis 被撑爆，也保证前端折线图只显示最近几分钟的趋势
                redisTemplate.opsForList().trim(redisKey, -50, -1);
                
                System.out.println("成功采集节点 [" + nodeName + "] 数据: " + dataRecord);
            }
        } catch (Exception e) {
            // 如果报错，通常是权限没配好（RBAC），或者是 Metrics Server 还没准备好
            System.err.println("数据采集失败，原因: " + e.getMessage());
        }
    }
}