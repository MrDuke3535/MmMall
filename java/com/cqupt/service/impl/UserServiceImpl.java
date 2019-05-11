package com.cqupt.service.impl;

import com.cqupt.mapper.UserMapper;
import com.cqupt.pojo.User;
import com.cqupt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }
}
