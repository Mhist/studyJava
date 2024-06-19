package com.mhist.studyJava.service;

import com.mhist.studyJava.pojo.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getList();

    void add(Article article);
}
