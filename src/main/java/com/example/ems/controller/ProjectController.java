package com.example.ems.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ems.converter.EmployeeConverter;
import com.example.ems.dto.ApiResponse;
import com.example.ems.dto.EmployeeDTO;

import com.example.ems.dto.ProjectDTO;
import com.example.ems.entities.Employee;

import com.example.ems.service.EmployeeService;
import com.example.ems.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired ProjectService projectService;
@Autowired EmployeeService employeeService;
@Autowired EmployeeConverter employeeConverter;



//@Autowired EmployeeProject employeeProjects;
//    public ProjectController(ProjectService projectService) {
//        this.projectService = projectService;
//    }

    @PostMapping("/addProject")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO createdProject = projectService.createProject(projectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable int id) {
        ProjectDTO project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable int id, @RequestBody ProjectDTO projectDTO) {
        ProjectDTO updatedProject = projectService.updateProject(id, projectDTO);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProject(@PathVariable int id) {
        this.projectService.deleteProject(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Project is  deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<ProjectDTO> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }
    @GetMapping("/{id}/employees")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByProjectId(@PathVariable int id) {
        List<EmployeeDTO> employeeDTOs = projectService.getEmployeesByProjectId(id);
        return ResponseEntity.ok(employeeDTOs);
    }
    
    @PostMapping("/{projectId}/employees/{employeeId}")
    public ResponseEntity<String> addEmployeeToProject(@PathVariable int projectId, @PathVariable int employeeId) {
        projectService.addEmployeeToProject(projectId, employeeId);
        // Retrieve the employee and project details
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);
        Employee employee = employeeConverter.toEntity(employeeDTO);
        ProjectDTO project = projectService.getProjectById(projectId);

        // Construct the response message
        String responseMessage = "Employee added to project successfully.\n";
        responseMessage += "Employee: " + employee.getName() + " (ID: " + employee.getId() + ")\n";
        responseMessage += "Project: " + project.getName() + " (ID: " + project.getId() + ")";
        return ResponseEntity.ok(responseMessage);
    }

 
    @DeleteMapping("/{projectId}/employees/{employeeId}")
    public ResponseEntity<String> removeEmployeeFromProject(@PathVariable int projectId, @PathVariable int employeeId) {
        projectService.removeEmployeeFromProject(projectId, employeeId);
        return ResponseEntity.ok("Employee removed from project successfully");
    }
}
