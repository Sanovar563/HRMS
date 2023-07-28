package com.example.ems.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ems.entities.EmployeeProject;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Integer> {
    List<EmployeeProject> findByProjectId(int projectId);
}

