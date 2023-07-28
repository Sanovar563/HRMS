package com.example.ems.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ems.dto.EmployeeProjectDTO;

import com.example.ems.service.EmployeeProjectService;
import com.example.ems.service.ProjectService;

@RestController
@RequestMapping("/employee-projects")
public class EmployeeProjectController {

    @Autowired EmployeeProjectService employeeProjectService;
@Autowired ProjectService projectService;


    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeProjectDTO>> getAllEmployeeProjects() {
        List<EmployeeProjectDTO> employeeProjects = employeeProjectService.getAllEmployeeProjects();
        return ResponseEntity.ok(employeeProjects);
    }
    
//    
//    @PostMapping("/{projectId}/employees/{employeeId}")
//    public ResponseEntity<String> addEmployeeToProject(@PathVariable int projectId, @PathVariable int employeeId) {
//        employeeProjectService.addEmployeeToProject(projectId, employeeId);
//        return ResponseEntity.ok("Employee added to project successfully");
//    }
}
