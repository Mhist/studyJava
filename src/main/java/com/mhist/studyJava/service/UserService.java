package com.mhist.studyJava.service;

import com.mhist.studyJava.pojo.User;

import java.util.List;

public interface UserService {


    /**
     * @param id
     * @return
     */
    User findById(Integer id);

    List<User> findAll();

    User createUser(User user);
}
