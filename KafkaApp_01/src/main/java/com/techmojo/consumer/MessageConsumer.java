package com.techmojo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
	@KafkaListener(topics = "topic01", groupId = "group01")
	public void listen(String message) {
		System.out.println("Received message: " + message);
	}
}
