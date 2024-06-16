package com.mhist.studyJava.service;

import com.mhist.studyJava.pojo.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    void register(String username, String password);

}
