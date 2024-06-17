package com.mhist.studyJava.mapper;

import com.mhist.studyJava.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ArticleMapper {
    @Select("select * from article")
    List<Article> getList();
}
