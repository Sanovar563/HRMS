package com.example.ems.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ems.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    // Custom query methods, if any
}
