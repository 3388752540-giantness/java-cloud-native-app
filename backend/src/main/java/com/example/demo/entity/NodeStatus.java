package com.example.demo.entity;
import lombok.Data;

@Data
public class NodeStatus {
    private Integer id;
    private String nodeName;
    private String status;
    private Integer cpuUsage;
    private Integer memUsage;
}