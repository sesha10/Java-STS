package com.techmojo;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.techmojo.entity.Department;
import com.techmojo.entity.Employee;
import com.techmojo.exception.InvalidDepartmentIdException;
import com.techmojo.service.DepartmentService;
import com.techmojo.service.EmployeeService;

@SpringBootApplication
public class JpaDemo01Application {

	public static void main(String[] args) {
		ApplicationContext ctxt = SpringApplication.run(JpaDemo01Application.class, args);
        EmployeeService employeeService = ctxt.getBean(EmployeeService.class);
        DepartmentService departmentService = ctxt.getBean(DepartmentService.class);
    	
    	Department department1 = new Department("Consular", "Hyderabad");
    	Department department2 = new Department("Guardian", "Bangalore");
    	departmentService.recruit(department1);
    	departmentService.recruit(department2);
    	
    	Employee employee1 = new Employee("Yoda", "yoda@jedi.com", department1);
    	Employee employee2 = new Employee("Kenobi", "kenobi@jedi.com", department2);
    	Employee employee3 = new Employee("Anakin", "anakin@jedi.com", department2);
    	
    	System.out.println("Before saving: " + employee1);
    	Employee savedEmployee = employeeService.recruit(employee1);
    	System.out.println("After saving: " + savedEmployee);
    	employeeService.recruit(employee2);
    	employeeService.recruit(employee3);
    	Employee employee4 = new Employee("Asoka", "asoka@jedi.com", department2);
    	Employee employee5 = new Employee("Quigon", "quigon@jedi.com", department1);
//    	
		try {
			department1 = departmentService.searchDepartment(2);
			List<Employee> employees = departmentService.findEmployeesByDepartment(department1);
	    	for (Employee employee : employees) {
				System.out.println(employee);
			}
		} catch (InvalidDepartmentIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
