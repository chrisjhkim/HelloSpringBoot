package com.chrisjhkim.demo;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloStsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloStsApplication.class, args);
	}

	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct");
//		MyData d1 = new MyData();
//		d1.setName("Chris Kim");
//		d1.setAge(35);
//		d1.setMail("chris1108@naver.com");
//		d1.setMemo("memo for chris kim");
//		repository.saveAndFlush(d1);
		
		
	}
}
