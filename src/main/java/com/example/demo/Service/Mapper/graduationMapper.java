package com.example.demo.Service.Mapper;


import com.example.demo.Service.pojo.Graduation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface graduationMapper {
     void insertExcel(Graduation g);
}
