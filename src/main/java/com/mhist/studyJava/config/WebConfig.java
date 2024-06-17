package com.mhist.studyJava.config;

import com.mhist.studyJava.interceptor.LoginInterceptor;
import com.mhist.studyJava.pojo.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private LoginInterceptor loginInterceptor;

    public WebConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 放行登录接口、注册接口
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login","/user/register");

    }
}
