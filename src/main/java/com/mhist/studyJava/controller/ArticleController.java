package com.mhist.studyJava.controller;
import com.mhist.studyJava.pojo.Article;
import com.mhist.studyJava.pojo.Result;
import com.mhist.studyJava.service.ArticleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public static Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }

}
