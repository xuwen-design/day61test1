package com.service.impl;

import com.mapper.UserMapper;
import com.pojo.User;
import com.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class userServiceimp implements userService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> showAll() {
        return userMapper.selectAll();
    }
}
