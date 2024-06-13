package com.mhist.studyJava.controller;
import com.mhist.studyJava.pojo.User;
import com.mhist.studyJava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findById")
    public User findById(Integer id) {
        return userService.findById(id);
    }

    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }





}
