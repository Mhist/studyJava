package com.mhist.studyJava.service;

import com.mhist.studyJava.pojo.Category;

import java.util.List;

public interface CategoryService {
    void createCategory(Category category);

    List<Category> getCategoryList();

    Category getCategoryDetail(Integer id);

    void updateCategory(Category category);
}
