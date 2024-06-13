package com.mhist.studyJava.mapper;

import com.mhist.studyJava.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where  id = #{id}")
    User findById(Integer id);

    @Select("select * from user")
    List<User> findAll();
    @Insert("insert into user (id ,name, age, gender, phone) VALUES (null, #{name}, #{age},#{gender}, #{phone})")
    void createUser(User user);
}
