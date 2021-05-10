package com.example.demo.Service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//老师看到的学生表逻辑实体
public class teacherStudent {
    int index;
    String stu_num;
    String name;
    float eva_value;
}
