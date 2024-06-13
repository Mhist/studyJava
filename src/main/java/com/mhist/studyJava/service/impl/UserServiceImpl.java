package com.mhist.studyJava.service.impl;

import com.mhist.studyJava.mapper.UserMapper;
import com.mhist.studyJava.pojo.User;
import com.mhist.studyJava.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public List<User> findAll(){
        return userMapper.findAll();
    }

    /**
     * @return
     */
    @Override
    public User createUser(User user) {
        userMapper.createUser(user);
        return user;
    }
}
