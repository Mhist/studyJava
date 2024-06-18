package com.mhist.studyJava.mapper;

import com.mhist.studyJava.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Insert("insert into category (category_name,category_alias,create_user,create_time,update_time) VALUES ( #{categoryName}, #{categoryAlias},#{createUser},now(), now())")
    void createCategory(Category category);

    @Select("select * from category")
    List<Category> getCategoryList();

    @Select("select * from category where  id=#{id}")
    Category getCategoryDetail(Integer id);

    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias},update_time=now() where id=#{id}")
    void updateCategory(Category category);
}
