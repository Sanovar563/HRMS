package com.example.ems.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.example.ems.converter.EmployeeProjectConverter;
import com.example.ems.dao.EmployeeProjectRepository;
import com.example.ems.dao.EmployeeRepository;
import com.example.ems.dao.ProjectRepository;
import com.example.ems.dto.EmployeeProjectDTO;

import com.example.ems.entities.EmployeeProject;

import com.example.ems.service.EmployeeProjectService;

@Service
public class EmployeeProjectServiceImpl implements EmployeeProjectService {

    @Autowired EmployeeProjectRepository employeeProjectRepository;
    @Autowired  EmployeeProjectConverter employeeProjectConverter;
@Autowired EmployeeProjectService employeeProjectService;
@Autowired ProjectRepository projectRepository;
   @Autowired EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeProjectDTO> getAllEmployeeProjects() {
        List<EmployeeProject> employeeProjects = employeeProjectRepository.findAll();
        return employeeProjectConverter.toDto(employeeProjects);
    }

}
