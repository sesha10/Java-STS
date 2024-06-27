package com.techmojo.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techmojo.entity.Department;
import com.techmojo.entity.Employee;
import com.techmojo.exception.InvalidDepartmentIdException;
import com.techmojo.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	private DepartmentService departmentService;
	
	@Autowired
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@GetMapping(value = "/{id}/employee")
	public ResponseEntity<List<Employee>> findAllEmployees(@PathVariable int id) throws InvalidDepartmentIdException {
		Department department = departmentService.searchDepartment(id);
		List<Employee> employees = departmentService.findEmployeesByDepartment(department);
		ResponseEntity<List<Employee>> responseEntity = new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping
	public ResponseEntity<List<Department>> findAllDepartments() {
		List<Department> departments = departmentService.listOfDepartments();
		ResponseEntity<List<Department>> responseEntity = new ResponseEntity<List<Department>>(departments, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Department> findDepartmentById(@PathVariable int id) throws InvalidDepartmentIdException {
		Department Department = departmentService.searchDepartment(id);
		ResponseEntity<Department> responseEntity = new ResponseEntity<Department>(Department, HttpStatus.FOUND);
		return responseEntity;
	}
	
	@PostMapping
	public ResponseEntity<Department> saveDepartment(@RequestBody Department Department) {
		Department savedDepartment = departmentService.recruit(Department);
		ResponseEntity<Department> responseEntity = new ResponseEntity<Department>(savedDepartment, HttpStatus.ACCEPTED);
		return responseEntity;
	}
	
//	@PutMapping(value = "/{id}")
//	public ResponseEntity<Department> editDepartment(@PathVariable int id, @RequestBody Department department) throws InvalidDepartmentIdException {
//		if (id != department.getId()) {
//			throw new InvalidDepartmentIdException("ID of the Department does not exist");
//		}
//		Department editedDepartment = departmentService.promote(department);
//		ResponseEntity<Department> responseEntity = new ResponseEntity<Department>(editedDepartment, HttpStatus.ACCEPTED);
//		return responseEntity;
//	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Department> deleteDepartmentById(@PathVariable int id) throws InvalidDepartmentIdException, SQLException {
		Department deletedDepartment = departmentService.terminate(id);
		ResponseEntity<Department> responseEntity = new ResponseEntity<Department>(deletedDepartment, HttpStatus.OK);
		return responseEntity;
	}
}
