package com.example.demo.Service.Mapper;


import com.example.demo.Service.pojo.courseIndexPoint;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
@Mapper
public interface courseIndexPointMapper {
    public List<courseIndexPoint> selectCourseIndexPoint(String stu_num,String num);
    public String selectIndexPointNumByContent(String content);
}
