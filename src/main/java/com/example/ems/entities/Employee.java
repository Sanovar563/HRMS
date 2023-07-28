package com.example.ems.entities;

import java.math.BigDecimal;


import java.util.*;
//import javax.persistence.CascadeType;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Employee name is required.")
    @Size(min = 3, message = "Employee name cannot exceed 100 characters.")
    private String name;

//    @NotBlank(message = "Username is required.")
//    @Size(max = 100, message = "Username cannot exceed 100 characters.")
//    private String username;
     

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.")
    private String email;

//    @NotBlank(message = "Password is required.")
//    private String password;

    @NotNull(message = "Salary is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0.")
    private BigDecimal salary;

//    @Min(value = 0, message = "Leave days cannot be negative.")
//    @Column(name = "leave_days") // Renamed column to "leave_days"
//    private int leaveDays;

    @NotNull(message = "Join date is required.")
    @PastOrPresent(message = "Join date must be in the past or present.")
    @Temporal(TemporalType.DATE)
    private Date joinDate;

    // Additional employee attributes
    private String department;
    private String position;
    
    @ManyToMany(mappedBy = "employees")
    private List<Project> projects = new ArrayList<>();
//    private String employeeId;
}
