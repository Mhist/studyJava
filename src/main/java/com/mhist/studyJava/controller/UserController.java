package com.mhist.studyJava.controller;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.mhist.studyJava.pojo.Result;
import com.mhist.studyJava.pojo.User;
import com.mhist.studyJava.service.UserService;
import com.mhist.studyJava.utils.ThreadlocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/user")
@Validated

public class UserController {

    @Value("${token.key}")
    private String tokenKey;
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private User getUser() {
        JWTPayload payload = ThreadlocalUtil.get();
        String username = (String) payload.getClaim("username");
        User user = userService.findByUsername(username);
        return user;
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

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$",message = "用户名长度应该在5-16字符之间") String username,@Pattern(regexp = "^\\S{5,16}$",message = "密码长度应该在5-16字符之间") String password) {
        User user = userService.findByUsername(username);
        if(user == null){
            return Result.error("当前用户不存在");
        }else {
            // 校验密码（假设你有一个已知的散列值）
            boolean isPasswordCorrect = Objects.equals(SecureUtil.md5(password), user.getPassword());
            if (isPasswordCorrect) {
//
                System.out.println((System.currentTimeMillis()));
                // 密钥
                byte[] key = tokenKey.getBytes();
                /*
                * https://www.toolhelper.cn/EncodeDecode/Base64?input=eyJpZCI6NSwidXNlcm5hbWUiOiJqaWFuZ3lhZG9uZyIsIm5pY2tuYW1lIjoiIn0&encoding=UTF-8&isFilterNonBase64=1&output=%7B%22id%22%3A5%2C%22username%22%3A%22jiangyadong%22%2C%22nickname%22%3A%22%22%7D
                * */
                String token = JWT.create()
                        .setPayload("id", user.getId())
                        .setPayload("username", user.getUsername())
                        .setPayload("nickname", user.getNickname())
                        .setKey(key)
                        .setExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12)) //添加过去时间
                        .sign();
                Map<String, String> data = new HashMap<>();
                data.put("token", token);

                return Result.success(data);
            }else {
                return Result.error("密码错误");
            }
        }

    }


    @GetMapping("/userInfo")
    public  Result<User> getUserInfo(@RequestHeader(name="Authorization") String token){
        User user = getUser();
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody  @Validated User user){
        // 修改更新时间
        user.setUpdateTime(LocalDateTime.now());
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatar){
        userService.updateAvatar(avatar);
        return Result.success();
    }

    @PatchMapping("/updatePassword")
    public Result updatePassword(@RequestBody Map<String,String> params){
        String oldPassword = params.get("oldPassword");
        String newPassword = params.get("newPassword");
        String rePassword = params.get("rePassword");
        Boolean anyOneIsEmpty = !StringUtils.hasLength(oldPassword) ||  !StringUtils.hasLength(oldPassword) ||  !StringUtils.hasLength(rePassword);
        if(anyOneIsEmpty){
            return Result.error("缺少必要的参数");
        }
        User user = getUser();
        // 老密码与原来的密码一致的话
        if(Objects.equals(SecureUtil.md5(oldPassword), user.getPassword())){
            if(newPassword.equals(rePassword)){
                String newPasswordMd5 = SecureUtil.md5(newPassword);
                userService.updatePassword(newPasswordMd5);
                return Result.success();
            }else {
                return Result.error("新密码与确认密码不一致");
            }
        }

        return Result.error("旧密码不正确");
    }



}
