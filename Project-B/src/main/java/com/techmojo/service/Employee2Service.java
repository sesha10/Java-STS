package com.techmojo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmojo.entity.Employee2;
import com.techmojo.repo.Employee2Repo;

@Service
public class Employee2Service {
	Employee2Repo employee2Repo;

	@Autowired
	public void setEmployee2Repo(Employee2Repo employee2Repo) {
		this.employee2Repo = employee2Repo;
	}
	
	public Employee2 saveEmployee(Employee2 employee2) {
		return employee2Repo.save(employee2);
	}
}
