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

    @Autowired
    private NodeService nodeService;

    @GetMapping("/nodes")
    public List<NodeStatus> getNodes() {
        return nodeService.getAllNodes();
    }
}