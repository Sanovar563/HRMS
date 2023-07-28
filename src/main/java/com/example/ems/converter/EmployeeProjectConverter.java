package com.example.ems.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ems.dto.EmployeeProjectDTO;
import com.example.ems.entities.EmployeeProject;

@Component
public class EmployeeProjectConverter {
	@Autowired 
	private ModelMapper modelmapper;
  @Autowired EmployeeConverter employeeConverter;
   @Autowired ProjectConverter projectConverter;

 

    public EmployeeProjectDTO toDto(EmployeeProject employeeProject) {
        EmployeeProjectDTO dto = this.modelmapper.map(employeeProject, EmployeeProjectDTO.class);
        
        return dto;
    }

    public List<EmployeeProjectDTO> toDto(List<EmployeeProject> employeeProjects) {
        return employeeProjects.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

