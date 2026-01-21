package com.example.demo.service;

import com.example.demo.entity.NodeStatus;
import com.example.demo.mapper.NodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NodeService {

    private final NodeMapper nodeMapper;

    @Autowired
    public NodeService(NodeMapper nodeMapper) {
        this.nodeMapper = nodeMapper;
    }

    public List<NodeStatus> getAllNodes() {
        return nodeMapper.findAll();
    }
}