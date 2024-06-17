package com.mhist.studyJava.service.impl;

import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.jwt.JWTPayload;
import com.mhist.studyJava.mapper.UserMapper;
import com.mhist.studyJava.pojo.User;
import com.mhist.studyJava.service.UserService;
import com.mhist.studyJava.utils.ThreadlocalUtil;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    /**
     * @param username
     * @param password
     * @return
     */
    @Override
    public void register(String username, String password) {

        // 加密
        String md5Password = SecureUtil.md5(password);
        userMapper.register(username, md5Password);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    /**
     * @param avatar
     */
    @Override
    public void updateAvatar(String avatar) {
        JWTPayload payload = ThreadlocalUtil.get();
        NumberWithFormat id = (NumberWithFormat) payload.getClaim("id");
        System.out.println(id);
        Integer ID = Integer.valueOf(String.valueOf(id));
        userMapper.updateAvatar(avatar,ID );
    }
}
