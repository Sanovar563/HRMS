package com.example.ems.service;

import java.util.List;



import com.example.ems.dto.EmployeeDTO;
import com.example.ems.dto.ProjectDTO;

public interface ProjectService {
    ProjectDTO createProject(ProjectDTO projectDTO);
    ProjectDTO getProjectById(int id);
    ProjectDTO updateProject(int id, ProjectDTO projectDTO);
    void deleteProject(int id);
    List<ProjectDTO> getAllProjects();
    List<EmployeeDTO> getEmployeesByProjectId(int projectId);
    
    
    
    
    void addEmployeeToProject(int projectId, int employeeId);
    void removeEmployeeFromProject(int projectId, int employeeId);
   


}