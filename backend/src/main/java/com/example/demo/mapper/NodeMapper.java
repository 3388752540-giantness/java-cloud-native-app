package com.example.demo.mapper;
import com.example.demo.entity.NodeStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface NodeMapper {
    @Select("SELECT * FROM node_status")
    List<NodeStatus> findAll();
}