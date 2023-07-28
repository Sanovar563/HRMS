package com.example.ems.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ems.entities.HR;



public interface HRRepository extends JpaRepository<HR, Long> {
    // Add custom query methods if needed
}