package com.techmojo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techmojo.producer.MessageProducer;

@RestController
public class KafkaController {
	MessageProducer messageProducer;

	@Autowired
	public void setMessageProducer(MessageProducer messageProducer) {
		this.messageProducer = messageProducer;
	}
	
	@PostMapping("/send")
	public String sendMessage(@RequestParam String message) {
		System.out.println("Before sending");
		messageProducer.sendMessage("topic01", message);
		return "Message sent: " + message;
	}
	
}
