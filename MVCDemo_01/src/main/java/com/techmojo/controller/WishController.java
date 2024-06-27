package com.techmojo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WishController {
	@RequestMapping("/hello")
	public String sayHello() {
		System.out.println("hello");
		return "Hello.jsp";
	}
	
	@RequestMapping("/hi")
	public String sayHi() {
		System.out.println("hi");
		return "Hi.jsp";
	}
	
	@RequestMapping("/welcome/{xyz}")
	public ModelAndView wayWelcome(@PathVariable("xyz") String name) {
		System.out.println("Welcome " + name);
		ModelAndView modelAndView = new ModelAndView("Welcome.jsp");
		modelAndView.addObject("name", name.trim());
		return modelAndView;
	}
}
