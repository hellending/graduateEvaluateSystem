package com.example.demo.Service.Mapper;


import com.example.demo.Service.pojo.studentEvaluation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface studentEvaluationMapper {
      void insertExcel1();
      void insertExcel2();
      void insertExcel3();
}
