package com.mhist.studyJava.mapper;

import com.mhist.studyJava.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface UserMapper {
    @Select("select * from user where  username = #{username}")
    User findByUsername(String username);

    @Insert("insert into user (username,password,create_time,update_time) VALUES ( #{username}, #{password},now(), now())")
    void register(String username, String password);

    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);

    @Update("update user set user_pic=#{avatar},update_time=now() where id=#{id}")
    void updateAvatar(String avatar,Integer id);

    @Update("update user set password=#{newPassword},update_time=now() where id=#{id}")
    void updatePassword(String newPassword, Integer id);
}
