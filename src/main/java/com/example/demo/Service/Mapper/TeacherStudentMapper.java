package com.example.demo.Service.Mapper;


import com.example.demo.Service.pojo.teacherStudent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
@Mapper
public interface TeacherStudentMapper {
    public teacherStudent getTeacherStudent(String stu_num);
}
