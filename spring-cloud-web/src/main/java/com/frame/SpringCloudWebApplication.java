package com.frame;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.frame.mapper")
public class SpringCloudWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudWebApplication.class, args);
	}
}
