package com.example.xenium;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value={"com.example.xenium.mapper"})
public class XeniumApplication {

	public static void main(String[] args) {
		SpringApplication.run(XeniumApplication.class, args);
	}

}
