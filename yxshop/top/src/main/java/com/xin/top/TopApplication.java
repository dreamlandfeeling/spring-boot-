package com.xin.top;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.xin.top.mapper")
@SpringBootApplication
public class TopApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopApplication.class, args);
	}
}
