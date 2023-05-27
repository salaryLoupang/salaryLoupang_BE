package com.project.loupang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class LoupangApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoupangApplication.class, args);
	}

}
