package com.mhist.studyJava.service.impl;

import cn.hutool.jwt.JWTPayload;
import com.mhist.studyJava.mapper.ArticleMapper;
import com.mhist.studyJava.pojo.Article;
import com.mhist.studyJava.pojo.User;
import com.mhist.studyJava.service.ArticleService;
import com.mhist.studyJava.service.UserService;
import com.mhist.studyJava.utils.ThreadlocalUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class ArticleServiceImpl implements ArticleService {
    private ArticleMapper articleMapper;

    private UserService userService;


    public User getUser() {
        JWTPayload payload = ThreadlocalUtil.get();
        String username = (String) payload.getClaim("username");
        User user = userService.findByUsername(username);
        return user;
    }

    public ArticleServiceImpl(ArticleMapper articleMapper, UserService userService) {
        this.articleMapper = articleMapper;
        this.userService = userService;
    }
    @Override
    public List<Article> getList(){
        return articleMapper.getList();
    }

    /**
     *
     */
    @Override
    public void add(Article article) {
        User user = getUser();
        article.setCreateUser(user.getId());
        articleMapper.add(article);
    }

    ;
}
