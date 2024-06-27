package com.techmojo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmojo.entity.Student;
import com.techmojo.exception.InvalidStudentIdException;
import com.techmojo.repo.StudentRepo;

@Service
public class StudentService {
	StudentRepo studentRepo;
	
	@Autowired
	public void setStudentRepo(StudentRepo studentRepo) {
		this.studentRepo = studentRepo;
	}
	
	public boolean terminate(int id) {
		try {
			searchStudent(id);
			studentRepo.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
	
	public Student recruit(Student student) {
		return studentRepo.save(student);
	}
	
	public List<Student> listOfStudents() {
		return studentRepo.findAll();
	}
	
	public Student searchStudent(int id) throws InvalidStudentIdException {
		Optional<Student> optStudent = studentRepo.findById(id);
		if(!optStudent.isPresent()) {
			throw new InvalidStudentIdException("Student with ID " + id + " is not valid");
		}
		return optStudent.get();
	}
}
