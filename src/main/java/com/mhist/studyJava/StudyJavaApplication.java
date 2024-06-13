package com.mhist.studyJava;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.mhist.studyJava.mapper")
@SpringBootApplication
public class StudyJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyJavaApplication.class, args);
	}

}
