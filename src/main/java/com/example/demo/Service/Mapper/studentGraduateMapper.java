package com.example.demo.Service.Mapper;

import com.example.demo.Service.pojo.studentGraduate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
@Mapper
public interface studentGraduateMapper {
    List<studentGraduate> selectStudentGraduate(String stu_num);
}
