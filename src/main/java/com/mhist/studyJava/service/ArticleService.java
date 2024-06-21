package com.mhist.studyJava.service;

import com.mhist.studyJava.pojo.Article;
import com.mhist.studyJava.pojo.PageBean;

public interface ArticleService {

    void add(Article article);

    PageBean<Article> getList(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
