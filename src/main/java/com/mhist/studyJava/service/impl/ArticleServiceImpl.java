package com.mhist.studyJava.service.impl;

import cn.hutool.jwt.JWTPayload;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mhist.studyJava.mapper.ArticleMapper;
import com.mhist.studyJava.pojo.Article;
import com.mhist.studyJava.pojo.PageBean;
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

    /**
     *
     */
    @Override
    public void add(Article article) {
        User user = getUser();
        article.setCreateUser(user.getId());
        articleMapper.add(article);
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @param state
     * @return
     */
    @Override
    public PageBean<Article> getList(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 创建page bean对象、
        PageBean<Article> pb = new PageBean<>();
        User user = getUser();
        Integer userId = user.getId();
        // 开启分页查询 pagehelper
        PageHelper.startPage(pageNum,pageSize);
        List<Article> list = articleMapper.getList(userId,categoryId,state);
        pb.setItems(list);
        pb.setTotal(list.size());
        return pb;

    }

    
}
