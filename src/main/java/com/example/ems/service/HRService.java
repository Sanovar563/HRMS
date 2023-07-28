package com.example.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ems.dto.EmployeeDTO;
import com.example.ems.dto.HRDTO;

@Service
public interface HRService {

	// HR login
//	  boolean login(String username, String password);
	 // Create a new HR profile
	  HRDTO createHRProfile(HRDTO hrDTO);
	  // Update HR profile
	  boolean updateHRProfile(HRDTO hrDTO);

	  // Get HR profile by ID
	  HRDTO getHRProfileById(Long hrId);

	  // Get all HR profiles
	  List<HRDTO> getAllHRProfiles();
	  
	 // Create a new employee profile
	  EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

	  // Update employee profile with relevant information
	  EmployeeDTO updateEmployee(int id, EmployeeDTO employeeDTO);

	  // Retrieve employee profile by employee ID
//	  EmployeeDTO getEmployeeProfileById(String employeeId);

	  // Get Employee by ID
	  public EmployeeDTO getEmployeeById(int employeeId);
	  // Retrieve employee profile by name
	  List<EmployeeDTO> getEmployeeProfilesByName(String name);

	  // Disable an employee profile
	  boolean disableEmployeeProfile(String employeeId);

	  // Enable a disabled employee profile
	  boolean enableEmployeeProfile(String employeeId);

	  // Check if an employee profile is disabled
	  boolean isEmployeeProfileDisabled(String employeeId);
	
}
