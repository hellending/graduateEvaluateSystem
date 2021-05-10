package com.example.demo.Controller;

import com.example.demo.Service.Mapper.studentMapper;
import com.example.demo.Service.Mapper.teacherMapper;
import com.example.demo.Service.pojo.student;
import com.example.demo.Service.pojo.teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class Login {
     @Autowired
     studentMapper student1;
     @Autowired
     teacherMapper teacher1;
     @RequestMapping("/studentLogin")
     String studentLogin(@RequestParam("userName") String userName, Model model, HttpSession session){
         student s = student1.selectStudentByNum(userName);
         if(s==null){
               model.addAttribute("msg","学生学号不存在");
               return "index";
         }
         else{
               session.setAttribute("identity","student");
               session.setAttribute("userName",userName);
               model.addAttribute("name",s.getName());
               model.addAttribute("sex",s.getSex());
               model.addAttribute("age",s.getAge());
               model.addAttribute("department",s.getDepartment());
               model.addAttribute("num",s.getStu_num());
               return "/studentHome";
         }
     }
     @RequestMapping("/teacherLogin")
     String teacherLogin(@RequestParam("userName") String userName,Model model, HttpSession session){
         teacher t = teacher1.selectTeacherByNum(userName);
         if(t==null){
               model.addAttribute("msg","教师工号不存在");
               return "index";
         }
         else{
             session.setAttribute("identity","teacher");
             session.setAttribute("userName",userName);
             return "/teacherHome";
         }
     }
}
