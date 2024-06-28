package com.techmojo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.techmojo.entity.Person;
import com.techmojo.producer.KafkaProducer;

@Service
public class DataInitializationService implements CommandLineRunner {
	KafkaProducer kafkaProducer;
	
	@Autowired
	public void setKafkaProducer(KafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			Person person = new Person(i, "Person " + i, "person"+i+"@test.com");
			kafkaProducer.produce(person);
		}
	}
	
}
