package com.mhist.studyJava.validation;

import com.mhist.studyJava.annotation.ArticleStateValidated;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

public class StateValidated implements ConstraintValidator<ArticleStateValidated, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        // 提供校验规则
        if(value == null){
            return false;
        }
        return value.equals("已发布") || value.equals("草稿");
    }

}
