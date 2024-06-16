package com.mhist.studyJava.controller;
import com.mhist.studyJava.pojo.Result;
import com.mhist.studyJava.pojo.User;
import com.mhist.studyJava.service.UserService;
import jakarta.validation.constraints.Pattern;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$",message = "用户名长度应该在5-16字符之间") String username,@Pattern(regexp = "^\\S{5,16}$",message = "密码长度应该在5-16字符之间") String password) {
        // 校验 :使用全局异常处理器处理参数校验失败的异常

        // 查询用户
        User user = userService.findByUsername(username);
        if(user == null){
            // 注册
            userService.register(username, password);
            return Result.success();
        }else {
            // 提示当前用户名已经被注册过了
            String message = "当前用户"+username+ "已经注册过了";
            return Result.error(message);
        }


    }





}
