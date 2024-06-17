package com.mhist.studyJava.service.impl;

import com.mhist.studyJava.mapper.ArticleMapper;
import com.mhist.studyJava.pojo.Article;
import com.mhist.studyJava.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class ArticleServiceImpl implements ArticleService {
    private ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }
    @Override
    public List<Article> getList(){
        return articleMapper.getList();
    };
}
