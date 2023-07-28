package com.example.ems.serviceimpl;

import java.util.*;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.ems.converter.ProjectConverter;

import com.example.ems.dao.EmployeeRepository;
import com.example.ems.dao.ProjectRepository;
import com.example.ems.dto.EmployeeDTO;

import com.example.ems.dto.ProjectDTO;
import com.example.ems.entities.Employee;

import com.example.ems.entities.Project;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
   
	
	@Autowired ProjectRepository projectRepository;
    @Autowired ProjectConverter projectConverter;
    @Autowired private ModelMapper modelmapper;
    @Autowired EmployeeRepository employeeRepository;
    
    
    
    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = projectConverter.toEntity(projectDTO);
        Project createdProject = projectRepository.save(project);
        return projectConverter.toDto(createdProject);
    }

    @Override
    public ProjectDTO getProjectById(int id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        return projectConverter.toDto(project);
    }

    @Override
    public ProjectDTO updateProject(int id, ProjectDTO projectDTO) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        // Update the existing project entity with the new data
        existingProject.setName(projectDTO.getName());
        existingProject.setStartDate(projectDTO.getStartDate());
        existingProject.setEndDate(projectDTO.getEndDate());
        existingProject.setDescription(projectDTO.getDescription());
        existingProject.setStatus(projectDTO.getStatus());

        Project updatedProject = projectRepository.save(existingProject);
        return projectConverter.toDto(updatedProject);
    }

    @Override
    public void deleteProject(int id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "Id", id));

        this.projectRepository.delete(project);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projectConverter.toDto(projects);
    }
    
   
    @Override
    public List<EmployeeDTO> getEmployeesByProjectId(int projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("Project not found"));
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee employee : project.getEmployees()) {
            EmployeeDTO employeeDTO = modelmapper.map(employee, EmployeeDTO.class);
            employeeDTOs.add(employeeDTO);
        }
        return employeeDTOs;
    }
       
    @Override
    public void addEmployeeToProject(int projectId, int employeeId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("Project not found"));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
       
        project.getEmployees().add(employee);
        projectRepository.save(project);
       
    }

    
    
    @Override
    public void removeEmployeeFromProject(int projectId, int employeeId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("Project not found"));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
        project.getEmployees().remove(employee);
        projectRepository.save(project);
    }
    
   
}
