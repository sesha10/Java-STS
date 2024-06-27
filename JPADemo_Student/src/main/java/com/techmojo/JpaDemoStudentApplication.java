package com.techmojo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.techmojo.entity.Student;
import com.techmojo.service.StudentService;

@SpringBootApplication
public class JpaDemoStudentApplication {

	public static void main(String[] args) {
		ApplicationContext ctxt = SpringApplication.run(JpaDemoStudentApplication.class, args);
		StudentService studentService = ctxt.getBean(StudentService.class);
		
		Student employee1 = new Student("Yoda", "yoda@jedi.com" );
		Student employee2 = new Student("Kenobi", "kenobi@jedi.com" );
		Student employee3 = new Student("Anakin", "anakin@jedi.com");
		
		studentService.recruit(employee3);
		studentService.recruit(employee2);
		studentService.recruit(employee1);
	}

}
