package com.techmojo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WishingController {
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
}
