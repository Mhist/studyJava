package com.mhist.studyJava.annotation;

import com.mhist.studyJava.validation.StateValidated;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotEmpty;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { StateValidated.class})
public @interface ArticleStateValidated {

    String message() default "文章状态只能是：已发布或者草稿";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
