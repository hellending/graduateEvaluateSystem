package com.example.demo.Service.Mapper;

import com.example.demo.Service.pojo.student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface studentMapper {
    student selectStudentByNum(String stu_num);
}
