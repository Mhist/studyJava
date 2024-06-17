package com.mhist.studyJava.service.impl;

import cn.hutool.jwt.JWTPayload;
import com.mhist.studyJava.mapper.CategoryMapper;
import com.mhist.studyJava.pojo.Category;
import com.mhist.studyJava.pojo.User;
import com.mhist.studyJava.service.CategoryService;
import com.mhist.studyJava.service.UserService;
import com.mhist.studyJava.utils.ThreadlocalUtil;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    private final UserService userService;

    public CategoryServiceImpl(CategoryMapper categoryMapper, UserService userService) {
        this.categoryMapper = categoryMapper;
        this.userService = userService;
    }

    public  User getUser() {
        JWTPayload payload = ThreadlocalUtil.get();
        String username = (String) payload.getClaim("username");
        return userService.findByUsername(username);
    }


    @Override
    public void createCategory(Category category) {
        User user = getUser();
        System.out.println(user.getId());
        Integer createUser = user.getId();
        category.setCreateUser(createUser);
        categoryMapper.createCategory(category);
    }
}
