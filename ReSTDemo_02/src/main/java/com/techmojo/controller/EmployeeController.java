package com.techmojo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techmojo.entity.Employee;
import com.techmojo.exception.InvalidEmployeeIdException;
import com.techmojo.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	private EmployeeService employeeService;
	
	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> findAllEmployees() {
		List<Employee> employees = employeeService.listOfEmployees();
		ResponseEntity<List<Employee>> responseEntity = new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Employee> findEmployeeById(@PathVariable int id) throws InvalidEmployeeIdException {
		Employee employee = employeeService.searchEmployee(id);
		ResponseEntity<Employee> responseEntity = new ResponseEntity<Employee>(employee, HttpStatus.FOUND);
		return responseEntity;
	}
	
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		Employee savedEmployee = employeeService.recruit(employee);
		ResponseEntity<Employee> responseEntity = new ResponseEntity<Employee>(savedEmployee, HttpStatus.ACCEPTED);
		return responseEntity;
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Employee> editEmployee(@PathVariable int id, @RequestBody Employee employee) throws InvalidEmployeeIdException {
		if (id != employee.getId()) {
			throw new InvalidEmployeeIdException("ID of the employee does not exist");
		}
		Employee editedEmployee = employeeService.promote(employee);
		ResponseEntity<Employee> responseEntity = new ResponseEntity<Employee>(editedEmployee, HttpStatus.ACCEPTED);
		return responseEntity;
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Employee> deleteEmployeeById(@PathVariable int id) throws InvalidEmployeeIdException {
		Employee deletedEmployee = employeeService.terminate(id);
		ResponseEntity<Employee> responseEntity = new ResponseEntity<Employee>(deletedEmployee, HttpStatus.OK);
		return responseEntity;
	}
}
