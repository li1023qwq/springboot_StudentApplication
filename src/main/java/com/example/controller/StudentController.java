package com.example.controller;

import com.example.entity.Student;
import com.example.entity.Class;
import com.example.service.StudentService;
import com.example.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private ClassMapper classMapper;
    
    @GetMapping("/list")
    public List<Student> list(@RequestParam(required = false) String keyword,
                            @RequestParam(required = false) Long classId) {
        return studentService.findByCondition(keyword, classId);
    }
    
    @GetMapping("/{id}")
    public Student detail(@PathVariable Long id) {
        return studentService.findById(id);
    }
    
    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            studentService.deleteById(id);
            result.put("success", true);
            result.put("message", "删除成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "删除失败：" + e.getMessage());
        }
        return result;
    }
    
    @GetMapping("/classes")
    public List<com.example.entity.Class> getClasses() {
        return classMapper.findAll();
    }
    
    @PostMapping("/save")
    public Map<String, Object> save(@RequestBody Student student) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 基本验证
            if (student == null) {
                throw new IllegalArgumentException("学生信息不能为空");
            }
            if (student.getStudentNo() == null || student.getStudentNo().trim().isEmpty()) {
                throw new IllegalArgumentException("学号不能为空");
            }
            if (student.getName() == null || student.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("姓名不能为空");
            }
            if (student.getAge() == null || student.getAge() < 0) {
                throw new IllegalArgumentException("年龄不合法");
            }
            if (student.getClassId() == null) {
                throw new IllegalArgumentException("请选择班级");
            }

            studentService.save(student);
            result.put("success", true);
            result.put("message", "保存成功");
        } catch (Exception e) {
            e.printStackTrace(); // 添加错误日志
            result.put("success", false);
            result.put("message", "保存失败：" + e.getMessage());
        }
        return result;
    }
} 