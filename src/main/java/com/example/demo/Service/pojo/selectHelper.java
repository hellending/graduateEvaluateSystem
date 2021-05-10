package com.example.demo.Service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
//用于统一查询的工具类
public class selectHelper {
    int code;
    String msg;
    int count;
    List<Object> data = new ArrayList<>();
}
