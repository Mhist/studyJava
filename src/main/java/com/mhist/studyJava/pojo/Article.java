package com.mhist.studyJava.pojo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Article {
    private Integer id;
    private String title;
    private String content;
    private String coverPic;
    private String state;
    private Integer categoryId;
    private String createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
