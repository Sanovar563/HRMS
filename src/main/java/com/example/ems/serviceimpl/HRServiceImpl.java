package com.example.ems.serviceimpl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ems.converter.EmployeeConverter;
import com.example.ems.converter.HRConverter;
import com.example.ems.dao.EmployeeRepository;
import com.example.ems.dao.HRRepository;
import com.example.ems.dto.EmployeeDTO;
import com.example.ems.dto.HRDTO;
import com.example.ems.entities.Employee;
import com.example.ems.entities.HR;
import com.example.ems.service.HRService;
@Service
public class HRServiceImpl implements HRService {

	
	 @Autowired EmployeeRepository employeeRepository; // Assuming the existence of an EmployeeRepository
	 @Autowired EmployeeConverter employeeConverter;
	 @Autowired
	 HRRepository hrRepository;
	 @Autowired HRConverter hrConverter;
//	  public HRServiceImpl(EmployeeRepository employeeRepository) {
//	    this.employeeRepository = employeeRepository;
//	  }

	 private Map<String, EmployeeDTO> employeeMap; // In-memory data store for simplicity

	  public HRServiceImpl() {
	    this.employeeMap = new HashMap<>();
	  }

	  private Map<Long, HRDTO> hrMap; // In-memory data store for simplicity
	  private List<EmployeeDTO> employeeList; // Assuming this is the data source for employee records

//	  public HRServiceImpl() {
//	    this.hrMap = new HashMap<>();
//	  }

//	  
//	  @Override
//	  public boolean login(String username, String password) {
//	    // Perform authentication logic
//	    // For example, check if the provided credentials are valid
//	    // Return true if the login is successful, false otherwise
//	    return "admin".equals(username) && "password".equals(password);
//	  }
	  @Override
	  public HRDTO createHRProfile(HRDTO hrDTO) {
	      // Perform necessary validations or business logic before creating the HR profile

	      // Create a new HR entity based on the HRDTO
	      HR hr = hrConverter.toEntity(hrDTO);

	      // Perform necessary database operations to save the HR entity
	      // For example:
	      hrRepository.save(hr);

	      // Map the created HR entity back to an HRDTO
	      HRDTO createdHRDTO = hrConverter.toDto(hr);

	      return createdHRDTO;
	  }
	  @Override
	  public boolean updateHRProfile(HRDTO hrDTO) {
	    Long hrId = hrDTO.getId();
	    if (hrMap.containsKey(hrId)) {
	      hrMap.put(hrId, hrDTO);
	      return true;
	    }
	    return false;
	  }

//	  @Override
//	  public HRDTO getHRProfileById(Long hrId) {
//	    return hrMap.get(hrId);
//	  }
//	  @Override
//	    public HRDTO getHRProfileById(Long hrId) {
//	        HRDTO hrProfile = hrMap.get(hrId);
//	        if (hrProfile != null) {
//	            return hrProfile;
//	        }
//	        throw new EntityNotFoundException("HR profile not found for id: " + hrId);
//	    }
	  @Override
	  public HRDTO getHRProfileById(Long hrId) {
		  
	      if (hrMap != null) {
	          HRDTO hrProfile = hrMap.get(hrId);
	          if (hrProfile != null) {
	              return hrProfile;
	          }
	      }
	      throw new EntityNotFoundException("HR profile not found for id: " + hrId);
	  }
	  
//	  @Override
//	    public EmployeeDTO getEmployeeById(int id) {
//	        Employee employee = employeeRepository.findById(id)
//	                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
//	        return employeeConverter.toDto(employee);
//	    }
	  @Override
	  public EmployeeDTO getEmployeeById(int employeeId) {
	    for (EmployeeDTO employee : employeeList) {
	      if (employee.getEmployeeId()==employeeId) {
	        return employee; // Found the employee with matching ID
	      }
	    }
	    return null; // Employee with the given ID not found
	  }

	  @Override
	  public List<HRDTO> getAllHRProfiles() {
	    return new ArrayList<>(hrMap.values());
	  }
	  @Override
	    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
	        Employee employee = employeeConverter.toEntity(employeeDTO);
	        Employee createdEmployee = employeeRepository.save(employee);
	        return employeeConverter.toDto(createdEmployee);
	    }

	  @Override
	    public EmployeeDTO updateEmployee(int id, EmployeeDTO employeeDTO) {
	        Employee existingEmployee = employeeRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

	        // Update the existing employee entity with the new data
	        existingEmployee.setName(employeeDTO.getName());
//	        existingEmployee.setUsername(employeeDTO.getUsername());
	        existingEmployee.setEmail(employeeDTO.getEmail());
	       
	        existingEmployee.setSalary(employeeDTO.getSalary());
//	        existingEmployee.setLeaveDays(employeeDTO.getLeaveDays());
	        existingEmployee.setJoinDate(employeeDTO.getJoinDate());
	        existingEmployee.setDepartment(employeeDTO.getDepartment());
	        existingEmployee.setPosition(employeeDTO.getPosition());

	        Employee updatedEmployee = employeeRepository.save(existingEmployee);
	        return employeeConverter.toDto(updatedEmployee);
	    }


//	  @Override
//	  public EmployeeDTO getEmployeeProfileById(String employeeId) {
//	    return employeeMap.get(employeeId);
//	  }

	  @Override
	  public List<EmployeeDTO> getEmployeeProfilesByName(String name) {
	    List<EmployeeDTO> matchingEmployees = new ArrayList<>();
	    for (EmployeeDTO employee : employeeMap.values()) {
	      if (employee.getName().equals(name)) {
	        matchingEmployees.add(employee);
	      }
	    }
	    return matchingEmployees;
	  }

	  @Override
	  public boolean disableEmployeeProfile(String employeeId) {
	    EmployeeDTO employee = employeeMap.get(employeeId);
	    if (employee != null) {
	      employee.setDisabled(true);
	      return true;
	    }
	    return false;
	  }

	  @Override
	  public boolean enableEmployeeProfile(String employeeId) {
	    EmployeeDTO employee = employeeMap.get(employeeId);
	    if (employee != null) {
	      employee.setDisabled(false);
	      return true;
	    }
	    return false;
	  }

	  @Override
	  public boolean isEmployeeProfileDisabled(String employeeId) {
	    EmployeeDTO employee = employeeMap.get(employeeId);
	    return employee != null && employee.isDisabled();
	  }
	
}
