package com.example.controller;

import com.example.entity.Class;
import com.example.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/class")
public class ClassController {
    @Autowired
    private ClassService classService;
    
    @GetMapping("/list")
    public List<Class> list(@RequestParam(required = false) String keyword) {
        return classService.findByKeyword(keyword);
    }
    
    @GetMapping("/{id}")
    public Class detail(@PathVariable Long id) {
        return classService.findById(id);
    }
    
    @PostMapping("/save")
    public Map<String, Object> save(@RequestBody Class classData) {
        Map<String, Object> result = new HashMap<>();
        try {
            classService.save(classData);
            result.put("success", true);
            result.put("message", "保存成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "保存失败：" + e.getMessage());
        }
        return result;
    }
    
    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            classService.deleteById(id);
            result.put("success", true);
            result.put("message", "删除成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "删除失败：" + e.getMessage());
        }
        return result;
    }
} 