package com.example.service;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    
    public boolean login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            return false;
        }
        // 使用MD5加密密码进行比较
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        return encryptedPassword.equals(user.getPassword());
    }
} 