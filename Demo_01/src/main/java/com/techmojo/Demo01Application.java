package com.techmojo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.techmojo.beans.FileService;

@SpringBootApplication
public class Demo01Application {

	public static void main(String[] args) {
		ApplicationContext ctxt = SpringApplication.run(Demo01Application.class, args);
		FileService fileService = ctxt.getBean(FileService.class);
		fileService.process();
	}

}
