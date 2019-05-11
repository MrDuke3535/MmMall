package com.cqupt.service;

import com.cqupt.pojo.User;

public interface UserService {
    public User getUserByName(String userName);
    public int insertUser(User user);
}
