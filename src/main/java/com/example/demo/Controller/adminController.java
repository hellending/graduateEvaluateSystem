package com.example.demo.Controller;

import com.example.demo.Service.Mapper.courseIndexRelationMapper;
import com.example.demo.Service.Mapper.graduationMapper;
import com.example.demo.Service.Mapper.indexMapper;
import com.example.demo.Service.pojo.*;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Index;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class adminController {
    @Autowired
    selectHelper ss;
    @Autowired
    courseIndexRelationMapper cr;
    @Autowired
    indexMapper im;
    @Autowired
    graduationMapper gm;
    @RequestMapping("/admin")
    public String toAdminHome(){
        return "adminHome";
    }

    @ResponseBody
    @RequestMapping("/adminSaveExcel")
    public String saveCourseIndexExcel(@RequestParam("file")MultipartFile file) throws IOException {
        String path =  ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/file/file2.xls";
        File tempFile = new File(path);
        file.transferTo(tempFile);
        return "1";
    }

    @ResponseBody
    @RequestMapping("/adminPreviewCourseIndexExcel")
    public selectHelper adminPreviewCourseIndexExcel() throws IOException {
        int i = 1;
        String path =  ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/file/file2.xls";
        File file = new File(path);
        InputStream in = new FileInputStream(file);
        Workbook book =  WorkbookFactory.create(in);
        List<courseIndexRelation> list = new ArrayList<>();
        Sheet sheet = book.getSheetAt(0);
        Cell cell0,cell1,cell2;
        int index = 1;
        while(true){
            try {
                cell0 = sheet.getRow(i).getCell(0);
                cell1 = sheet.getRow(i).getCell(1);
                cell2 = sheet.getRow(i).getCell(2);
                cell0.setCellType(CellType.STRING);
                cell1.setCellType(CellType.STRING);
                courseIndexRelation cp = new courseIndexRelation();
                cp.setCourse_num(cell0.getStringCellValue());
                cp.setIndex_num(cell1.getStringCellValue());
                cp.setWeight((float) cell2.getNumericCellValue());
                cp.setIndex(index++);
                list.add(cp);
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
    @RequestMapping("/adminPreviewIndexExcel")
    public selectHelper adminPreviewIndexExcel() throws IOException {
        int i = 1;
        String path =  ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/file/file2.xls";
        File file = new File(path);
        InputStream in = new FileInputStream(file);
        Workbook book =  WorkbookFactory.create(in);
        List<index> list = new ArrayList<>();
        Sheet sheet = book.getSheetAt(0);
        Cell cell0,cell1;
        int index = 1;
        while(true){
            try {
                cell0 = sheet.getRow(i).getCell(0);
                cell1 = sheet.getRow(i).getCell(1);
                cell0.setCellType(CellType.STRING);
                cell1.setCellType(CellType.STRING);
                index p = new index();
                p.setNum(cell0.getStringCellValue());
                p.setContent(cell1.getStringCellValue());
                p.setIndex(index++);
                list.add(p);
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
    @RequestMapping("/adminPreviewGraduationExcel")
    public selectHelper adminPreviewGraduationExcel() throws IOException {
        int i = 1;
        String path =  ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/file/file2.xls";
        File file = new File(path);
        InputStream in = new FileInputStream(file);
        Workbook book =  WorkbookFactory.create(in);
        List<Graduation> list = new ArrayList<>();
        Sheet sheet = book.getSheetAt(0);
        Cell cell0,cell1;
        int index = 1;
        while(true){
            try {
                cell0 = sheet.getRow(i).getCell(0);
                cell1 = sheet.getRow(i).getCell(1);
                cell0.setCellType(CellType.STRING);
                cell1.setCellType(CellType.STRING);
                Graduation p = new Graduation();
                p.setNum(cell0.getStringCellValue());
                p.setContent(cell1.getStringCellValue());
                p.setIndex(index++);
                list.add(p);
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
    @RequestMapping("/adminInsertCourseIndexExcel")
    public void adminInsertCourseIndexExcel() throws IOException {
        int i = 1;
        String path =  ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/file/file2.xls";
        File file = new File(path);
        InputStream in = new FileInputStream(file);
        Workbook book =  WorkbookFactory.create(in);
        List<courseIndexRelation> list = new ArrayList<>();
        Sheet sheet = book.getSheetAt(0);
        Cell cell0,cell1,cell2;
        int index = 1;
        while(true){
            try {
                cell0 = sheet.getRow(i).getCell(0);
                cell1 = sheet.getRow(i).getCell(1);
                cell2 = sheet.getRow(i).getCell(2);
                cell0.setCellType(CellType.STRING);
                cell1.setCellType(CellType.STRING);
                courseIndexRelation cp = new courseIndexRelation();
                cp.setCourse_num(cell0.getStringCellValue());
                cp.setIndex_num(cell1.getStringCellValue());
                cp.setWeight((float) cell2.getNumericCellValue());
                cp.setIndex(index++);
                list.add(cp);
                i++;
            }catch(Exception e){
                break;
            }
        }
        for(courseIndexRelation c:list){
            cr.insertCourse_Index(c);
        }
    }

    @ResponseBody
    @RequestMapping("/adminInsertIndexExcel")
    public void adminInsertIndexExcel() throws IOException {
        int i = 1;
        String path =  ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/file/file2.xls";
        File file = new File(path);
        InputStream in = new FileInputStream(file);
        Workbook book =  WorkbookFactory.create(in);
        List<index> list = new ArrayList<>();
        Sheet sheet = book.getSheetAt(0);
        Cell cell0,cell1;
        int index = 1;
        while(true){
            try {
                cell0 = sheet.getRow(i).getCell(0);
                cell1 = sheet.getRow(i).getCell(1);
                cell0.setCellType(CellType.STRING);
                cell1.setCellType(CellType.STRING);
                index p = new index();
                p.setNum(cell0.getStringCellValue());
                p.setContent(cell1.getStringCellValue());
                p.setIndex(index++);
                list.add(p);
                i++;
            }catch(Exception e){
                break;
            }
        }
        for(index c:list){
            im.InsertExcel(c);
        }
    }

    @ResponseBody
    @RequestMapping("/adminInsertGraduationExcel")
    public void insertGraduationExcel() throws IOException {
        int i = 1;
        String path =  ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/file/file2.xls";
        File file = new File(path);
        InputStream in = new FileInputStream(file);
        Workbook book =  WorkbookFactory.create(in);
        List<Graduation> list = new ArrayList<>();
        Sheet sheet = book.getSheetAt(0);
        Cell cell0,cell1;
        int index = 1;
        while(true){
            try {
                cell0 = sheet.getRow(i).getCell(0);
                cell1 = sheet.getRow(i).getCell(1);
                cell0.setCellType(CellType.STRING);
                cell1.setCellType(CellType.STRING);
                Graduation p = new Graduation();
                p.setNum(cell0.getStringCellValue());
                p.setContent(cell1.getStringCellValue());
                p.setIndex(index++);
                list.add(p);
                i++;
            }catch(Exception e){
                break;
            }
        }
        for(Graduation c:list){
            gm.insertExcel(c);
        }
    }
    @ResponseBody
    @RequestMapping("/adminReformatExcel")
    public void adminReformatExcel() throws IOException {
        String path =  ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/file/file2.xls";
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
    @RequestMapping("/demo")
    public selectHelper demo(){
        return new selectHelper();
    }
}
