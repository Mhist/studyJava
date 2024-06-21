package com.mhist.studyJava.mapper;

import com.mhist.studyJava.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {



    @Insert("insert into article (title,content,cover_pic,state,create_user,category_id,create_time,update_time) VALUES (#{title}, #{content},#{coverPic}, #{state}, #{createUser},#{categoryId},now(), now())")
    void add(Article article);

    /*映射配置文件*/
    List<Article> getList(Integer userId, Integer categoryId,  String state);
}
