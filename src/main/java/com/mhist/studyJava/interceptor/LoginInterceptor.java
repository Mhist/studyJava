package com.mhist.studyJava.interceptor;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.mhist.studyJava.utils.ThreadlocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private RedisTemplate redisTemplate;
    private final Environment environment;

    public LoginInterceptor(RedisTemplate redisTemplate, Environment environment) {
        this.redisTemplate = redisTemplate;
        this.environment = environment;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = false;
        // 令牌
        try{
            String token = request.getHeader("Authorization").replace("Bearer ","");
            String tokenKey = environment.getProperty("token.key");
            JWT jwt = JWTUtil.parseToken(token);

            // 把业务数据存储到ThreadLocal中
            ThreadlocalUtil.set(jwt.getPayload());
            boolean verify = JWTUtil.verify(token, tokenKey.getBytes());
            String tokenCache = (String) redisTemplate.opsForValue().get("token");
            Boolean correct = token.equals(tokenCache) ? true : false;
            if(verify && correct) {
                result = true;
            }else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                // 设置响应内容类型为JSON
   //设置响应的内容类型为文本/html，根据需要也可以设置为"text/plain"等
                response.setContentType("text/html;charset=UTF-8");

// 获取PrintWriter对象，用于向响应流写入数据
                PrintWriter writer = response.getWriter();

// 写入具体的文本内容
                writer.write("未登录或登录凭证无效");

// 刷新缓冲区，确保数据立即写入到客户端，对于某些情况这一步可能是必须的
                writer.flush();

// 关闭writer，释放资源
                writer.close();
            }
        }catch (Exception e){
            // 设置响应状态码，例如401 Unauthorized
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            // 设置响应内容类型为JSON
            response.setContentType("application/json");
            // 输出错误信息给客户端
            PrintWriter writer = response.getWriter();
            writer.write("登录失败，请检查用户名和密码。");
            writer.flush();
            writer.close();
        }

        return result;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清空 ThreadLocal
        ThreadlocalUtil.remove();

    }
}
