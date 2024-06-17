package com.mhist.studyJava.mapper;

import com.mhist.studyJava.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    @Insert("insert into category (category_name,category_alias,create_user,create_time,update_time) VALUES ( #{categoryName}, #{categoryAlias},#{createUser},now(), now())")
    void createCategory(Category category);
}
