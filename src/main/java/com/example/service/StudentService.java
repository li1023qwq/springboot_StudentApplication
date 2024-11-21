package com.example.service;

import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;
    
    public List<Student> findAll() {
        return studentMapper.findAll();
    }
    
    public Student findById(Long id) {
        return studentMapper.findById(id);
    }
    
    public List<Student> findByKeyword(String keyword) {
        return studentMapper.findByCondition(keyword, null);
    }
    
    public List<Student> findByCondition(String keyword, Long classId) {
        return studentMapper.findByCondition(keyword, classId);
    }
    
    public void deleteById(Long id) {
        studentMapper.deleteById(id);
    }
    
    @Transactional
    public void save(Student student) {
        // 检查学号是否已存在
        Student existing = studentMapper.findByStudentNo(student.getStudentNo());
        if (existing != null && !existing.getId().equals(student.getId())) {
            throw new RuntimeException("学号已存在");
        }

        if (student.getId() == null) {
            studentMapper.insert(student);
        } else {
            studentMapper.update(student);
        }
    }
} 