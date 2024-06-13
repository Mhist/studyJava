package com.mhist.studyJava.mapper;

import com.mhist.studyJava.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where  id = #{id}")
    public User findById(Integer id);

    @Select("select * from user")
    List<User> findAll();
}
