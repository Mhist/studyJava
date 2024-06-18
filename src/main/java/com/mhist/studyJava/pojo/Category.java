package com.mhist.studyJava.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Category {
    /*
    *  @NotNull  必须传
    *  @NotEmpty  必须传且不能为空字符串
    * */
    @NotNull(groups = Update.class)
    private Integer id;
    @NotEmpty
    private String categoryName;
    @NotEmpty
    private String categoryAlias;
    private Integer createUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /*
    * 如果说某个校验项没有指定分组、默认属于Default分组
    * 分组之间可以继承  A extend B 那么A中拥有B中所有的校验项
    *
    * */

    public interface Add extends Default {

    }

    public interface Update  extends Default {

    }
}
