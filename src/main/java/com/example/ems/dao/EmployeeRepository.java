package com.example.ems.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ems.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//    Optional<Employee> findByEmail(String email);
//	public Employee findById(int id);
}
