package com.mhist.studyJava.service;

import com.mhist.studyJava.pojo.User;


public interface UserService {

    User findByUsername(String username);

    void register(String username, String password);

    void update(User user);

    void updateAvatar(String avatar);
}
