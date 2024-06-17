package com.mhist.studyJava.controller;
import com.mhist.studyJava.pojo.Article;
import com.mhist.studyJava.pojo.Result;
import com.mhist.studyJava.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private static ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
    @GetMapping("/list")
    public static Result getList(){
        List<Article> articleList = articleService.getList();
        return Result.success(articleList);
    }
}
