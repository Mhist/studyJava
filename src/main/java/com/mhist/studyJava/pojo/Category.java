package com.mhist.studyJava.pojo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Category {
    private Integer id;
    private String categoryName;
    private String categoryAlias;
    private String createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
