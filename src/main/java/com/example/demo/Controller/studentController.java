package com.example.demo.Controller;

import com.example.demo.Service.Mapper.courseIndexPointMapper;
import com.example.demo.Service.Mapper.studentGraduateMapper;
import com.example.demo.Service.Mapper.studentIndexPointMapper;
import com.example.demo.Service.pojo.courseIndexPoint;
import com.example.demo.Service.pojo.selectHelper;
import com.example.demo.Service.pojo.studentGraduate;
import com.example.demo.Service.pojo.studentIndexPoint;
import org.apache.coyote.http11.filters.IdentityOutputFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class studentController {
    @Autowired
    studentGraduateMapper s;
    @Autowired
    studentIndexPointMapper sp;
    @Autowired
    courseIndexPointMapper cp;
    @Autowired
    selectHelper ss;
    @ResponseBody
    @RequestMapping("/graduateEvaluationInfo")
    public selectHelper getGraduateEvaluationInfo(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        ss.setCode(0);
        ss.setCount(1);
        ss.setMsg(":");
        List<Object> data = new ArrayList<>();
        List<studentGraduate> sg = new ArrayList<>();
        sg.addAll(s.selectStudentGraduate(userName));
        int j = 1;
        for(studentGraduate i:sg){
            i.setIndex(j++);
            data.add(i);
        }
        ss.setData(data);
        return ss;
    }
    @ResponseBody
    @RequestMapping("/indexPointEvaluationValueInfo")
    public selectHelper getIndexPointEvaluationValueInfo(@RequestParam("content") String content){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        ss.setCode(0);
        ss.setCount(1);
        ss.setMsg(":");
        List<Object> data = new ArrayList<>();
        List<studentIndexPoint> list = new ArrayList<>();
        String graduateRequirementsNum = sp.selectGraduateRequirementsNum(content);
        list.addAll(sp.selectStudentIndexPoint(graduateRequirementsNum,userName));
        int j = 1;
        for(studentIndexPoint i:list){
            i.setIndex(j++);
             data.add(i);
        }
        ss.setData(data);
        return ss;
    }
    @ResponseBody
    @RequestMapping("/getCourseIndexPointInfo")
    public selectHelper getCourseIndexPointInfo(@RequestParam("content") String content){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        ss.setCode(0);
        ss.setCount(1);
        ss.setMsg(":");
        List<Object> data = new ArrayList<>();
        List<courseIndexPoint> list = new ArrayList<>();
        String indexPointNum = cp.selectIndexPointNumByContent(content);
        list.addAll(cp.selectCourseIndexPoint(userName,indexPointNum));
        System.out.println(list.toString());
        int j = 1;
        for(courseIndexPoint i:list){
            i.setIndex(j++);
            data.add(i);
        }
        ss.setData(data);
        System.out.println(ss.toString());
        return ss;
    }
}
