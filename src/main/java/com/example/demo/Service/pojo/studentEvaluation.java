package com.example.demo.Service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class studentEvaluation {
    String stu_num;
    String name;
    float in1;
    float in2;
    float in3;
    float in4;
    float in5;
    float in6;
    int index;
}
