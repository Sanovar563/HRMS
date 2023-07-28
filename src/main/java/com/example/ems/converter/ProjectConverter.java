package com.example.ems.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ems.dto.ProjectDTO;
import com.example.ems.entities.Project;



@Component
public class ProjectConverter {
	@Autowired 
	private ModelMapper modelmapper;
	
	 public ProjectDTO toDto(Project project) {
	        ProjectDTO dto = this.modelmapper.map(project, ProjectDTO.class);
//        dto.setId(project.getId());
//        dto.setName(project.getName());
//        dto.setStartDate(project.getStartDate());
//        dto.setEndDate(project.getEndDate());
//        dto.setDescription(project.getDescription());
//        dto.setStatus(project.getStatus());
        return dto;
    }
	 public List<ProjectDTO> toDto(List<Project> project)
		{
			return project.stream().map(x-> toDto(x)).collect(Collectors.toList());
		}

	 public Project toEntity(ProjectDTO dto) {
	        Project project = this.modelmapper.map(dto, Project.class);
//        project.setId(dto.getId());
//        project.setName(dto.getName());
//        project.setStartDate(dto.getStartDate());
//        project.setEndDate(dto.getEndDate());
//        project.setDescription(dto.getDescription());
//        project.setStatus(dto.getStatus());
        return project;
    }
	 public List<Project> toEntity(List<ProjectDTO> dto)
		{
			return dto.stream().map(x->toEntity(x)).collect(Collectors.toList());
		}
}
