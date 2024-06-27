package com.techmojo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmojo.entity.Department;
import com.techmojo.entity.Employee;
import com.techmojo.exception.InvalidDepartmentIdException;
import com.techmojo.repo.DepartmentRepo;
import com.techmojo.repo.EmployeeRepo;

@Service
public class DepartmentService {
	DepartmentRepo departmentRepo;
	EmployeeRepo employeeRepo;
	
	@Autowired
	public void setEmployeeRepo(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	@Autowired
	public void setDepartmentRepo(DepartmentRepo departmentRepo) {
		this.departmentRepo = departmentRepo;
	}
	
	public List<Employee> findEmployeesByDepartment(Department department) {
		return employeeRepo.findAllByDepartment(department);
	}
	
	public boolean terminate(int id) {
		try {
			searchDepartment(id);
			departmentRepo.deleteById(id);
		} catch (InvalidDepartmentIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public Department recruit(Department department) {
		return departmentRepo.save(department);
	}
	
	public List<Department> listOfDepartments() {
		return departmentRepo.findAll();
	}
	
	public Department searchDepartment(int id) throws InvalidDepartmentIdException {
		Optional<Department> optDepartment = departmentRepo.findById(id);
		if(!optDepartment.isPresent()) {
			throw new InvalidDepartmentIdException("Department with ID " + id + " is not valid");
		}
		return optDepartment.get();
	}
}
