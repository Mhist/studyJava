package com.mhist.studyJava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonBuilderUtils;

@Configuration
public class CommonConfig{

    /**
     * 如果Country是第三方对象的情况下
     */

//    @Bean
//    public Country country(){
//        return new Conutry();
//    }


    //    @Bean("aa")   如果未指定name属性、则会使用方法名
//    public Province province(Country country){
//        System.out.println("province:" + country);
//        return new Province();
//    }

    /**
     * 如果方法的内容需要使用ioc容器中已经存在的bean对象、那么只需要在方法上声明即可、spring全自动注入
     */

}
