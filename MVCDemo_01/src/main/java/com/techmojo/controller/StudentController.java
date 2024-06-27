package com.techmojo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.techmojo.model.Student;

@Controller
public class StudentController {
	@RequestMapping("/student")
	public ModelAndView method1() {
		ModelAndView modelAndView = new ModelAndView("Student.jsp");
		Student student = new Student(101, "Yoda", "yoda@jedi.com");
		modelAndView.addObject("abcd", student);
		return modelAndView;
	}
}
