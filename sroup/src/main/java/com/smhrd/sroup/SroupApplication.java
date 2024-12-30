package com.smhrd.sroup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan(basePackages = "com.smhrd.sroup.mapper")
public class SroupApplication {

	public static void main(String[] args) {
		SpringApplication.run(SroupApplication.class, args);
	}

}
