package com.mhist.studyJava.controller;
import com.mhist.studyJava.pojo.Article;
import com.mhist.studyJava.pojo.PageBean;
import com.mhist.studyJava.pojo.Result;
import com.mhist.studyJava.service.ArticleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private static ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
    @GetMapping("/list")
    public Result<PageBean<Article>> getList(Integer pageNum,
                                             Integer pageSize,
                                             @RequestParam(required = false) Integer categoryId,
                                             @RequestParam(required = false) String state){
       PageBean<Article> pageBean = articleService.getList(pageNum,pageSize,categoryId,state);
        return Result.success(pageBean);
    }

    @PostMapping("/add")
    public static Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }



}
