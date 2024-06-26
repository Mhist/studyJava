package com.mhist.studyJava.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public final class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void saveCache(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getCache(String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
