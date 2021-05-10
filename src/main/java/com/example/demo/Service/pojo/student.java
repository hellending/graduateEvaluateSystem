package com.example.demo.Service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class student {
    String stu_num;
    String name;
    String sex;
    int age;
    String department;
}
