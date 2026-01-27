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
}