package com.techmojo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ResponseBody
	public String sayHelloGET() {
		return "Hello World GET method()";
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	@ResponseBody
	public String sayHelloPOST() {
		return "Hello World POST method()";
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.PUT)
	@ResponseBody
	public String sayHelloPUT() {
		return "Hello World PUT method()";
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.DELETE)
	@ResponseBody
	public String sayHelloDELETE() {
		return "Hello World DELETE method()";
	}
}
