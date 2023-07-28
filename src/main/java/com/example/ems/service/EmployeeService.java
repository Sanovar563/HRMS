package com.example.ems.service;

import java.util.List;


import com.example.ems.dto.EmployeeDTO;
import com.example.ems.dto.ProjectDTO;



public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO getEmployeeById(int id);
    EmployeeDTO updateEmployee(int id, EmployeeDTO employeeDTO);
//    void deleteEmployee(int id);
    List<EmployeeDTO> getAllEmployees();
    List<ProjectDTO> getProjectsByEmployeeId(int employeeId);
 
    void addEmployeeToProject(int employeeId, int projectId);
    void removeEmployeeFromProject(int employeeId, int projectId);
    
}
