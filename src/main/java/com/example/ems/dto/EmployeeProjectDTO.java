package com.example.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeProjectDTO {
	    private int id;
	    private EmployeeDTO employee;
	    private ProjectDTO project;
		

}

