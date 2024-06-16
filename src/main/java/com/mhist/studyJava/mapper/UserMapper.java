package com.mhist.studyJava.mapper;

import com.mhist.studyJava.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
    @Select("select * from user where  username = #{username}")
    User findByUsername(String username);

    @Insert("insert into user (username,password,create_time,update_time) VALUES ( #{username}, #{password},now(), now())")
    void register(String username, String password);
}
