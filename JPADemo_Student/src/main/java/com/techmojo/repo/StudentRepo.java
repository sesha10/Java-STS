package com.techmojo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techmojo.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

}
