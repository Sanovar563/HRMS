package com.example.ems.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

	 private int id;
	    private String name;
	    private Date startDate;
	    private Date endDate;
	    private String description;
	    private String status;

	
}
