package com.example.ems.dto;

import java.math.BigDecimal;


import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

	
	    private int id;
	    @NotBlank(message = "Employee name is required.")
	    @Size(min =3, message = "Employee name cannot exceed 100 characters.")
	    private String name;

	    @NotBlank(message = "Email is required.")
	    @Email(message = "Invalid email format.")
	    private String email;
//	    private String password;
	    @NotNull(message = "Salary is required.")
	    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0.")
	    private BigDecimal salary;
//	    private int leaveDays;
	    @NotNull(message = "Join date is required.")
	    @PastOrPresent(message = "Join date must be in the past or present.")
	    @Temporal(TemporalType.DATE)
	    private Date joinDate;
	    private String department;
	    private String position;
	    private boolean disabled;
	  
	    private int employeeId;
	  
	}

