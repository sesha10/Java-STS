package com.techmojo.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techmojo.entity.Person;
import com.techmojo.repo.PersonRepo;

@Component
public class KafkaConsumer {
	PersonRepo personRepo;

	@Autowired
	public void setPersonRepo(PersonRepo personRepo) {
		this.personRepo = personRepo;
	}
	
	@KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(String message) {
		System.out.println("Consumed Message: " + message);
		ObjectMapper mapper = new ObjectMapper();
		try {
			Person person = mapper.readValue(message, Person.class);
			personRepo.save(person);
			System.out.println("Saved to DB: " + person);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
