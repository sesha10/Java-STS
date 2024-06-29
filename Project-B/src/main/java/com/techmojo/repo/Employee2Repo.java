package com.techmojo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techmojo.entity.Employee2;

@Repository
public interface Employee2Repo extends JpaRepository<Employee2, Integer> {

}
