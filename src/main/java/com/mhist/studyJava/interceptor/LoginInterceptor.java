package com.mhist.studyJava.interceptor;

import cn.hutool.jwt.JWTUtil;

import com.mhist.studyJava.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private Environment environment;

    public LoginInterceptor(Environment environment) {
        this.environment = environment;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = false;
        // 令牌
        try{
            String token = request.getHeader("Authorization").replace("Bearer ","");
            String tokenKey = environment.getProperty("token.key");
            System.out.println(token);
            System.out.println(tokenKey);
            boolean verify = JWTUtil.verify(token, tokenKey.getBytes());
            if(verify) {
                result = true;
            }
        }catch (Exception e){
            response.setStatus(401);
        }
        return result;
    }
}
