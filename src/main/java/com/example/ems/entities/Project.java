package com.example.ems.entities;

import java.util.Date;





import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Project name is required.")
    @Size(max = 100, message = "Project name cannot exceed 100 characters.")
    private String name;

    @NotNull(message = "Start date is required.")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @NotNull(message = "End date is required.")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    // Additional project attributes
    private String description;
    private String status;

//    @ManyToMany(mappedBy = "projects" , cascade = CascadeType.ALL)
//    @JsonIgnoreProperties
//    private List<Employee> employees;
//    @ManyToMany(mappedBy = "projects" )   //, cascade = CascadeType.ALL)
//    private List<Employee> employees;
//    
    @ManyToMany
    @JoinTable(
        name = "employee_project",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employees = new ArrayList<>();
//    @OneToMany(mappedBy = "projects",cascade = CascadeType.ALL)
//    @JsonIgnoreProperties
//    private List<Employee> employees;

       

}

