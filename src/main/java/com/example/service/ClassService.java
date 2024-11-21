package com.example.service;

import com.example.entity.Class;
import com.example.mapper.ClassMapper;
import com.example.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClassService {
    @Autowired
    private ClassMapper classMapper;
    
    @Autowired
    private StudentMapper studentMapper;
    
    public List<Class> findByKeyword(String keyword) {
        List<Class> classes = classMapper.findByKeyword(keyword);
        // 为每个班级添加学生数量
        classes.forEach(cls -> {
            int count = studentMapper.countByClassId(cls.getId());
            cls.setStudentCount(count);
        });
        return classes;
    }
    
    public Class findById(Long id) {
        return classMapper.findById(id);
    }
    
    @Transactional
    public void save(Class classData) {
        if (classData.getId() == null) {
            classMapper.insert(classData);
        } else {
            classMapper.update(classData);
        }
    }
    
    @Transactional
    public void deleteById(Long id) {
        // 检查班级是否还有学生
        int studentCount = studentMapper.countByClassId(id);
        if (studentCount > 0) {
            throw new RuntimeException("该班级还有学生，不能删除");
        }
        classMapper.deleteById(id);
    }
} 