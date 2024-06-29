package com.techmojo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techmojo.entity.Employee;
import com.techmojo.exception.InvalidEmployeeIdException;
import com.techmojo.repo.EmployeeRepo;

@Service
public class EmployeeService {
	EmployeeRepo employeeRepo;
	RedisTemplate<String, Employee> redisTemplate;
	ObjectMapper objectMapper;
	KafkaProducerService kafkaProducerService;
	private static final String CACHE_KEY_PREFIX = "Employee::";
	
	@Autowired
	public void setKafkaProducerService(KafkaProducerService kafkaProducerService) {
		this.kafkaProducerService = kafkaProducerService;
	}

	@Autowired
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Autowired
	public void setRedisTemplate(RedisTemplate<String, Employee> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Autowired
	public void setEmployeeRepo(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	
	// Delete existing employee
	public Employee deleteEmployee(int id) throws InvalidEmployeeIdException {
		Employee delEmployee = searchEmployee(id);
		employeeRepo.deleteById(id);	
		
		String cacheKey = CACHE_KEY_PREFIX + id;
		redisTemplate.delete(cacheKey);
		
		String message = "Employee with id " + id + " is deleted";
		System.out.println("Message deleted of Kafka: " + message);
		kafkaProducerService.sendMessage(message);
		
		return delEmployee;
	}
	
	// Add, Save employee
	public Employee recruitEmployee(Employee employee) {
		Employee savedEmployee = employeeRepo.save(employee);
		
		String cacheKey = CACHE_KEY_PREFIX + savedEmployee.getId();
		redisTemplate.opsForValue().set(cacheKey, savedEmployee);
		
		try {
			String message = objectMapper.writeValueAsString(savedEmployee);
			System.out.println("Message to Kafka: " + message);
			kafkaProducerService.sendMessage(message);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return savedEmployee;
	}
	
	// Get list of all Employees
	public List<Employee> listOfEmployees() {
		return employeeRepo.findAll();
	}
	
	// Search, Find for existing Employee
	public Employee searchEmployee(int id) throws InvalidEmployeeIdException {
		String cacheKey = "Cached " + CACHE_KEY_PREFIX + id;
		System.out.println(cacheKey);
		
		Employee employee = redisTemplate.opsForValue().get(cacheKey);
		if (employee == null) {
			employee = employeeRepo.findById(id).orElse(null);
			if (employee != null) {
				redisTemplate.opsForValue().set(cacheKey, employee);
			}
			else {
				throw new InvalidEmployeeIdException("Employee with ID " + id + " is not valid");
			}
		}
		return employee;
	}
	
	// Edit existing Employee
	public Employee editEmployee(Employee employee) throws InvalidEmployeeIdException {		
		searchEmployee(employee.getId());
		employeeRepo.save(employee);
		
		String cacheKey = CACHE_KEY_PREFIX + employee.getId();
		redisTemplate.opsForValue().set(cacheKey, employee);
		
		try {
			String message = objectMapper.writeValueAsString(cacheKey);
			System.out.println("Message edited to kafka: " + message);
			kafkaProducerService.sendMessage(message);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employee;
	}
}
