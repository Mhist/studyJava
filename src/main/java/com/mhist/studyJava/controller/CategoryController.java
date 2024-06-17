package com.mhist.studyJava.controller;

import com.mhist.studyJava.pojo.Category;
import com.mhist.studyJava.pojo.Result;
import com.mhist.studyJava.service.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/createCategory")
    public Result createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return Result.success();
    }
}
