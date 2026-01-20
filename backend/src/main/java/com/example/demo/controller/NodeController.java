package com.example.demo.controller;

import com.example.demo.entity.NodeStatus;
import com.example.demo.service.NodeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin // 允许 Vue 前端跨域访问
public class NodeController {

    private final NodeService nodeService;

    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @GetMapping("/nodes")
    public List<NodeStatus> getNodes() {
        return nodeService.getAllNodes();
    }
}