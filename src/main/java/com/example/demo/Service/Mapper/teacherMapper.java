package com.example.demo.Service.Mapper;

import com.example.demo.Service.pojo.teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
@Mapper
public interface teacherMapper {
    teacher selectTeacherByNum(String number);
    List<String> selectCourseByNumber(String number);
}
