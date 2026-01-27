package com.example.demo.controller;

import com.example.demo.entity.NodeStatus;
import com.example.demo.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin // 允许 Vue 前端跨域访问
public class NodeController {

    public NodeController(NodeService nodeService) {
    this.nodeService = nodeService;
    // 加上这行，启动时如果在日志里看到它，说明 Controller 绝对加载成功了
    System.out.println("====== NodeController 已加载，准备就绪！======");
    }

    @Autowired
    private NodeService nodeService;

    @GetMapping("/nodes")
    public List<NodeStatus> getNodes() {
        return nodeService.getAllNodes();
    }

    @GetMapping("/metrics/{nodeName}")
    public List<Map<String, String>> getNodeMetrics(@PathVariable String nodeName) {
    // 1. 从 Redis 获取最近 50 条数据
    String redisKey = "metrics:node:" + nodeName;
    List<String> rawData = redisTemplate.opsForList().range(redisKey, 0, -1);

    // 2. 将 "时间|CPU|内存" 格式解析为 JSON 列表
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