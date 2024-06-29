package com.techmojo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_tb")
public class Employee2 {
	@Id
	int id;
	String name;
	String email;
	String department;
	public Employee2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee2(int id, String name, String email, String department) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.department = department;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "Employee2 [id=" + id + ", name=" + name + ", email=" + email + ", department=" + department + "]";
	}
	
}
