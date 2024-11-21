package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private Long id;
    private String studentNo;
    private String name;
    private Integer age;
    private String gender;
    private String phone;
    private Long classId;
    private Class studentClass;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
} 