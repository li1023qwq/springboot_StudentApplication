package com.example.util;

import com.example.entity.Student;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    public static List<Student> importStudents(InputStream inputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        List<Student> students = new ArrayList<>();
        
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Student student = new Student();
            student.setStudentNo(getCellValue(row.getCell(0)));
            student.setName(getCellValue(row.getCell(1)));
            student.setAge(Integer.parseInt(getCellValue(row.getCell(2))));
            student.setGender(getCellValue(row.getCell(3)));
            student.setPhone(getCellValue(row.getCell(4)));
            students.add(student);
        }
        
        workbook.close();
        return students;
    }
    
    public static void exportStudents(HttpServletResponse response, List<Student> students) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("学生信息");
        
        // 创建表头
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("学号");
        headerRow.createCell(1).setCellValue("姓名");
        headerRow.createCell(2).setCellValue("年龄");
        headerRow.createCell(3).setCellValue("性别");
        headerRow.createCell(4).setCellValue("电话");
        headerRow.createCell(5).setCellValue("班级");
        
        // 填充数据
        int rowNum = 1;
        for (Student student : students) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(student.getStudentNo());
            row.createCell(1).setCellValue(student.getName());
            row.createCell(2).setCellValue(student.getAge());
            row.createCell(3).setCellValue(student.getGender());
            row.createCell(4).setCellValue(student.getPhone());
            row.createCell(5).setCellValue(student.getStudentClass() != null ? 
                student.getStudentClass().getClassName() + "(" + student.getStudentClass().getGrade() + ")" : "");
        }
        
        // 设置响应头
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=students.xlsx");
        
        // 写入响应
        workbook.write(response.getOutputStream());
        workbook.close();
    }
    
    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            default:
                return "";
        }
    }
} 