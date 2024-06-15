package com.mhist.studyJava;

import com.mhist.studyJava.annotation.EnableCommonConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@MapperScan("com.mhist.studyJava.mapper")
/* 使用 @Import注解 */
//@Import(Xxx.class)
@SpringBootApplication
//@Import(CommonConfig.class)
//@Import({CommonConfig.class,CommonConfig.class,CommonConfig.class,CommonConfig.class})   // 当有多个配置文件时、可以使用数组形式传值
//@Import(CommonImportSelector.class)
@EnableCommonConfig
public class StudyJavaApplication {

	public static void main(String[] args) {

	ApplicationContext context =  SpringApplication.run(StudyJavaApplication.class, args);
		System.out.println(context);
	}

	/*
	*   @SpringBootApplication注解只识别位于启动类当前包所在目录以及其子包、所以如果文件位于外部的话需要手动通过
	* 	@ComponentScan指定basePackage
	*
	*   如果需要引入第三方的jar包、让其能够被自动扫描到、可以使用 @Bean 注解或者是 @import 注解
	* 	@Bean注解可以指定一个方法、方法的返回值会被自动注入到容器之中
	*
	* */

	// 注入外部对象
	//    @Bean
	//    public Country country(){
	//        return new Conutry();
	//    }





}
