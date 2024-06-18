package com.mhist.studyJava.controller;

import com.mhist.studyJava.pojo.Category;
import com.mhist.studyJava.pojo.Result;
import com.mhist.studyJava.service.CategoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/categoryList")
    public Result<List<Category>> getCategoryList(){
        List<Category> cs = categoryService.getCategoryList();
        return Result.success(cs);
    }


    @GetMapping("/categoryDetail")
    public Result<Category> getCategoryDetail(@RequestParam Integer id){
        Category category = categoryService.getCategoryDetail(id);
        return Result.success(category);
    }


    @PutMapping("/updateCategory")
    public Result updateCategory(@RequestBody @Validated Category category){
        categoryService.updateCategory(category);
        return Result.success();
    }




}
