package com.example.demo.Service.Mapper;


import com.example.demo.Service.pojo.studentIndexPoint;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Mapper
public interface studentIndexPointMapper {
     List<studentIndexPoint> selectStudentIndexPoint(String num,String stu_num);
     String selectGraduateRequirementsNum(String content);
}
