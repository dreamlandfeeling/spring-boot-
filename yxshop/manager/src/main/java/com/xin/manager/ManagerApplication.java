package com.xin.manager;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Properties;
@MapperScan("com.xin.manager.mapper")
@AutoConfigureAfter(MybatisAutoConfiguration.class)  //这里就是保证xml在MybatisAutoConfiguration完成配置的核心
@ServletComponentScan("com.xin.manager.filter")
@SpringBootApplication
public class ManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
	}



	//@Bean
	//public PageHelper pageHelper(){
	//	PageHelper pageHelper = new PageHelper();
	//	Properties properties = new Properties();
	//	properties.setProperty("offsetAsPageNum","true");
	//	properties.setProperty("rowBoundsWithCount","true");
	//	properties.setProperty("reasonable","true");
	//	properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
	//	pageHelper.setProperties(properties);
	//	return pageHelper;
	//}

}
