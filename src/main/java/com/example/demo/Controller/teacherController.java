package com.example.demo.Controller;


import com.example.demo.Service.Mapper.*;
import com.example.demo.Service.pojo.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
public class teacherController {
    @Autowired
    selectHelper ss;
    @Autowired
    studentEvaluationMapper s;
    @Autowired
    teacherMapper t;
    @Autowired
    TeacherStudentMapper ts;
    @Autowired
    studentGraduateMapper sgm;
    @Autowired
    studentIndexPointMapper sp;
    @Autowired
    courseIndexPointMapper cp;

    static selectHelper sss = new selectHelper();
    @ResponseBody
    @RequestMapping("/saveTempExcel")
    public void saveTempExcel(@RequestParam("file")MultipartFile file) throws IOException {
        System.gc();
        String path =  ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/file/file.xls";
        File tempFile = new File(path);
        file.transferTo(tempFile);
    }
    @ResponseBody
    @RequestMapping("/previewExcel")
    public selectHelper previewExcel() throws IOException, InvalidFormatException {
        int i = 1;
        String path =  ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/file/file.xls";
        File file = new File(path);
        InputStream in = new FileInputStream(file);
        Workbook book =  WorkbookFactory.create(in);
        List<studentEvaluation> list = new ArrayList<>();
        Sheet sheet = book.getSheetAt(0);
        Cell cell0,cell1,cell2,cell3,cell4,cell5,cell6,cell7;
        int index = 1;
        while(true){
            try {
                cell0 = sheet.getRow(i).getCell(0);
                cell1 = sheet.getRow(i).getCell(1);
                cell2 = sheet.getRow(i).getCell(2);
                cell3 = sheet.getRow(i).getCell(3);
                cell4 = sheet.getRow(i).getCell(4);
                cell5 = sheet.getRow(i).getCell(5);
                cell6 = sheet.getRow(i).getCell(6);
                cell7 = sheet.getRow(i).getCell(7);
                cell0.setCellType(CellType.STRING);
                cell1.setCellType(CellType.STRING);
                cell2.setCellType(CellType.STRING);
                cell3.setCellType(CellType.STRING);
                cell4.setCellType(CellType.STRING);
                cell5.setCellType(CellType.STRING);
                cell6.setCellType(CellType.STRING);
                cell7.setCellType(CellType.STRING);
                studentEvaluation sgt = new studentEvaluation();
                sgt.setStu_num(cell0.getStringCellValue());
                sgt.setName(cell1.getStringCellValue());
                sgt.setIn1(Float.parseFloat(cell2.getStringCellValue()));
                sgt.setIn2(Float.parseFloat(cell3.getStringCellValue()));
                sgt.setIn3(Float.parseFloat(cell4.getStringCellValue()));
                sgt.setIn4(Float.parseFloat(cell5.getStringCellValue()));
                sgt.setIn5(Float.parseFloat(cell6.getStringCellValue()));
                sgt.setIn6(Float.parseFloat(cell7.getStringCellValue()));
                sgt.setIndex(index++);
                list.add(sgt);
                i++;
            }catch(Exception e){
                break;
            }
        }
        List<Object> data = new ArrayList<>();
        data.addAll(list);
        ss.setCode(0);
        ss.setCount(1);
        ss.setMsg(":");
        ss.setData(data);
        return ss;
    }

    @ResponseBody
    @RequestMapping("/insertExcel")
    public void insertExcel(@RequestParam("file")MultipartFile file1) throws IOException, InvalidFormatException {
        s.insertExcel1();
        s.insertExcel2();
        s.insertExcel3();
    }

    @ResponseBody
    @RequestMapping("/reformatExcel")
    public void reformatExcel() throws Exception {
        String path =  ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/file/file.xls";
        File file = new File(path);
        file.delete();
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("sheet1");
        book.write(new FileOutputStream(new File(path)));
        book.close();
        //解除文件占用
        System.gc();
    }

    @ResponseBody
    @RequestMapping("/getClassType")
    public List<String> getClassType(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        List<String> list = new ArrayList<>();
        list.addAll(t.selectCourseByNumber(userName));
        return list;
    }


    @ResponseBody
    @RequestMapping("/getSelectedStudent")
    public selectHelper getSelectedStudent(@RequestParam("stu_num")String stu_num){
        String userName = stu_num;
        ss.setCode(0);
        ss.setCount(1);
        ss.setMsg(":");
        List<Object> data = new ArrayList<>();
        List<studentGraduate> sg = new ArrayList<>();
        sg.addAll(sgm.selectStudentGraduate(userName));
        int j = 1;
        for(studentGraduate i:sg){
            i.setIndex(j++);
            data.add(i);
        }
        ss.setData(data);
        return ss;
    }

    @ResponseBody
    @RequestMapping("/getStudentIndexPointEvaluationValueInfo")
    public selectHelper getStudentIndexPointEvaluationValueInfo(@RequestParam("stu_num") String stu_num,
                                                                @RequestParam("content") String content){
        String userName = stu_num;
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
    @RequestMapping("/getStudentCourseIndexPointInfo")
    public selectHelper getStudentCourseIndexPointInfo(@RequestParam("stu_num") String stu_num,
                                                       @RequestParam("content") String content){
        String userName = stu_num;
        ss.setCode(0);
        ss.setCount(1);
        ss.setMsg(":");
        List<Object> data = new ArrayList<>();
        List<courseIndexPoint> list = new ArrayList<>();
        String indexPointNum = cp.selectIndexPointNumByContent(content);
        list.addAll(cp.selectCourseIndexPoint(userName,indexPointNum));
        int j = 1;
        for(courseIndexPoint i:list){
            i.setIndex(j++);
            data.add(i);
        }
        ss.setData(data);
        return ss;
    }

    @ResponseBody
    @RequestMapping("/readExcel")
    public selectHelper readExcel(@RequestParam("file") MultipartFile file) throws IOException {
        String path =  ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/file/file1.xls";
        File file1 = new File(path);
        file.transferTo(file1);
        InputStream in = new FileInputStream(file1);
        Workbook book =  WorkbookFactory.create(in);
        Sheet sheet = book.getSheetAt(0);
        Cell cell0;
        int i = 1;
        List<String> sn = new ArrayList<>();
        while(true){
            try {
                cell0 = sheet.getRow(i).getCell(0);
                studentEvaluation sgt = new studentEvaluation();
                cell0.setCellType(CellType.STRING);
                sn.add(cell0.getStringCellValue());
                i++;
            }catch(Exception e){
                break;
            }
        }
        List<teacherStudent> list = new ArrayList<>();
        int x =1;
        for(String j:sn){
            list.add(ts.getTeacherStudent(j));
        }
        List<Object> data = new ArrayList<>();
        for(teacherStudent m:list){
            if(m!=null) {
                m.setIndex(x++);
                data.add(m);
            }
        }
        sss.setCode(0);
        sss.setCount(1);
        sss.setMsg(":");
        sss.setData(data);
        return sss;
    }

    @ResponseBody
    @RequestMapping("/singleSelect")
    public selectHelper singleSelect(@RequestParam("stu_num") String stu_num){
        teacherStudent x = ts.getTeacherStudent(stu_num);
        List<Object> data = new ArrayList<>();
        sss.setCode(0);
        sss.setCount(1);
        sss.setMsg(":");
        data.add(x);
        sss.setData(data);
        return sss;
    }

    @RequestMapping("/downloadModel")
    public void downloadModel1(HttpServletResponse response,@RequestParam("fileName")String fileName) throws IOException {
        //待下载文件名
        //设置为png格式的文件
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setContentType("application/octet-stream");
        response.setHeader("content-disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;
        outputStream = response.getOutputStream();
        String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/model/";
        bis = new BufferedInputStream(new FileInputStream(new File(path + fileName )));
        int read = bis.read(buff);

        //通过while循环写入到指定了的文件夹中
        while (read != -1) {
            outputStream.write(buff, 0, read);
            outputStream.flush();
            read = bis.read(buff);
        }
        bis.close();
        outputStream.close();
    }

    @ResponseBody
    @RequestMapping("/readExcelResponse")
    public selectHelper readExcelResponse(){
        return sss;
    }
}
