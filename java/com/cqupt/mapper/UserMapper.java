package com.cqupt.mapper;

import com.cqupt.pojo.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface UserMapper {
    public int insertUser(User user);
    public User getUserByName(String userName);
}
