package com.example.demo.Service.Mapper;

import com.example.demo.Service.pojo.index;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface indexMapper {
    void InsertExcel(index i);
}
