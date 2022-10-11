package com.learn.spring.UnitTest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnitTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnitTestApplication.class, args);
		// for(String bean:ac.getBeanDefinitionNames()) {
		// 	System.out.println(bean);
		// }

	}

}
