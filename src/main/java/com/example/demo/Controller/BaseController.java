package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
    @RequestMapping("/teacherHome")
    public String toTeacherHome() {
        return "teacherHome";
    }
    @RequestMapping("/studentHome")
    public String toStudentHome(){
        return "studentHome";
    }
    @RequestMapping("/load")
    public String toLoad() {
        return "index";
    }
}
