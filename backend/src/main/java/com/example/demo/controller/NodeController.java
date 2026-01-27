package com.example.demo.controller;

import com.example.demo.entity.NodeStatus;
import com.example.demo.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate; // 必须引入 Redis 工具类
import org.springframework.web.bind.annotation.*; // 引入 @PathVariable, @GetMapping 等

import java.util.*; // 必须引入 List, Map, ArrayList, HashMap

@RestController
@RequestMapping("/api")
@CrossOrigin // 允许 Vue 前端跨域访问
public class NodeController {

    private final NodeService nodeService;

    // 1. 声明 redisTemplate 变量并自动注入
    @Autowired
    private StringRedisTemplate redisTemplate;

    // 2. 构造函数注入 NodeService (推荐写法)
    @Autowired
    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
        System.out.println("====== NodeController 已加载，准备就绪！======");
    }

    // 接口一：获取 MySQL 里的节点基础信息
    @GetMapping("/nodes")
    public List<NodeStatus> getNodes() {
        return nodeService.getAllNodes();
    }

    // 接口二：从 Redis 获取最近 50 条实时监控指标
    @GetMapping("/metrics/{nodeName}")
    public List<Map<String, String>> getNodeMetrics(@PathVariable String nodeName) {
        // 这里的 Key 必须和 MonitorService 存入的 Key 一致
        String redisKey = "metrics:node:" + nodeName;
        
        // 1. 从 Redis 获取 List 里的所有数据 (0 到 -1 代表全部)
        List<String> rawData = redisTemplate.opsForList().range(redisKey, 0, -1);

        // 2. 将 "时间|CPU|内存" 这种原始字符串解析为前端好处理的 JSON 对象
        List<Map<String, String>> result = new ArrayList<>();
        if (rawData != null) {
            for (String record : rawData) {
                String[] parts = record.split("\\|");
                if (parts.length == 3) {
                    Map<String, String> map = new HashMap<>();
                    map.put("time", parts[0]);
                    map.put("cpu", parts[1]);
                    map.put("mem", parts[2]);
                    result.add(map);
                }
            }
        }
        return result;
    }
}