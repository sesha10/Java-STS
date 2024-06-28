package com.techmojo.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.techmojo.entity.Person;

@Component
public class KafkaProducer {
	KafkaTemplate<String, String> kafkaTemp;
	
	@Autowired
	public void setKafkaTemp(KafkaTemplate<String, String> kafkaTemp) {
		this.kafkaTemp = kafkaTemp;
	}

	@Value("${kafka.topic}")
	String kafkaTopic;
	
	public void produce(Person person) {
		String jsonString = "{\"id\":" + person.getId() 
		+ ",\"name\":\"" + person.getName() 
		+ "\"," + "\"email\":\"" + person.getEmail() + "\""
		+ "}";
		
		kafkaTemp.send(kafkaTopic, jsonString);
		System.out.println("Produced Message: " + jsonString);
	}
}
