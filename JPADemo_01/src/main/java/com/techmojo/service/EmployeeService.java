package com.techmojo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmojo.entity.Employee;
import com.techmojo.exception.InvalidEmployeeIdException;
import com.techmojo.repo.EmployeeRepo;

@Service
public class EmployeeService {
	EmployeeRepo employeeRepo;
	
	@Autowired
	public void setEmployeeRepo(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	
	public boolean terminate(int id) {
		try {
			searchEmployee(id);
			employeeRepo.deleteById(id);
		} catch (InvalidEmployeeIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public Employee recruit(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	public List<Employee> listOfEmployees() {
		return employeeRepo.findAll();
	}
	
	public Employee searchEmployee(int id) throws InvalidEmployeeIdException {
		Optional<Employee> optEmployee = employeeRepo.findById(id);
		if(!optEmployee.isPresent()) {
			throw new InvalidEmployeeIdException("Employee with ID " + id + " is not valid");
		}
		return optEmployee.get();
	}
	
	public Employee promote(Employee employee) throws InvalidEmployeeIdException {		
		searchEmployee(employee.getId());
		return employeeRepo.save(employee);
	}
}
