package com.example.user.service.impl;

import com.example.user.mapper.UserMapper;
import com.example.user.pojo.User;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryById(Long id) {
        return userMapper.selectById(id);
    }
}
