package com.example.demo.Service.Mapper;

import com.example.demo.Service.pojo.courseIndexRelation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface courseIndexRelationMapper {
      void insertCourse_Index(courseIndexRelation c);
}
