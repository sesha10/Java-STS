package com.techmojo.producer;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
	KafkaTemplate<String, String> kafkaTemp;

	@Autowired
	public void setKafkaTemp(KafkaTemplate<String, String> kafkaTemp) {
		this.kafkaTemp = kafkaTemp;
	}
	
	public void sendMessage(String topic, String message) {
//		kafkaTemp.send(topic, message);
		CompletableFuture<SendResult<String, String>> future = kafkaTemp.send(topic, message);
		future.whenComplete((result, ex) -> {
			if (ex == null) {
				System.out.println("Sent message: [" + message + "] with offset: [" + result.getRecordMetadata().offset() + "]");
			}
			else {
				System.out.println("Unable to send the message: [" + message + "] due to: " + ex.getMessage());
			}
		});
	}	
}
