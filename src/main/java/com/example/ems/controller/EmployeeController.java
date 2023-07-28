package com.example.ems.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ems.dto.ApiResponse;
import com.example.ems.dto.EmployeeDTO;
import com.example.ems.dto.ProjectDTO;
import com.example.ems.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	 @Autowired EmployeeService employeeService;


	 
//create
	 @PostMapping("/addEmp")
	    public ResponseEntity<EmployeeDTO> createEmployee( @Valid @RequestBody EmployeeDTO employeeDTO) {
	        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
	    }

	 //get by id
	    @GetMapping("/{id}")
	    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int id) {
	        EmployeeDTO employee = employeeService.getEmployeeById(id);
	        return ResponseEntity.ok(employee);
	    }
	    
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ApiResponse handleValidationException(MethodArgumentNotValidException ex) {
	        BindingResult result = ex.getBindingResult();
	        StringBuilder errors = new StringBuilder();
	        for (FieldError error : result.getFieldErrors()) {
	            errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
	        }
	        return new ApiResponse(errors.toString(), false);
	    }
	    

	    //update
	    @PutMapping("/{id}")
	    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable int id, @RequestBody EmployeeDTO employeeDTO) {
	        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
	        return ResponseEntity.ok(updatedEmployee);
	    }

//	    //delete
//	    @DeleteMapping("/{id}")
//	    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable int id) {
//	       this.employeeService.deleteEmployee(id);
//	        return new ResponseEntity<ApiResponse>(new ApiResponse("Employee is deleted successfully", true), HttpStatus.OK);
//	    }
//
//	    //get all
	    @GetMapping("/getAll")
	    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
	        List<EmployeeDTO> employees = employeeService.getAllEmployees();
	        return ResponseEntity.ok(employees);
	    }
	    
	    //get project by id
	    @GetMapping("/{id}/projects")
	    public ResponseEntity<List<ProjectDTO>> getProjectsByEmployeeId(@PathVariable int id) {
	        List<ProjectDTO> projectDTOs = employeeService.getProjectsByEmployeeId(id);
	        return ResponseEntity.ok(projectDTOs);
	    }
	    
	    //add employee to project
	    @PostMapping("/{employeeId}/projects/{projectId}")
	    public ResponseEntity<String> addEmployeeToProject(@PathVariable int employeeId, @PathVariable int projectId) {
	        employeeService.addEmployeeToProject(employeeId, projectId);
	        return ResponseEntity.ok("Employee added to project successfully");
	    }

	    //delete
	    @DeleteMapping("/{employeeId}/projects/{projectId}")
	    public ResponseEntity<String> removeEmployeeFromProject(@PathVariable int employeeId, @PathVariable int projectId) {
	        employeeService.removeEmployeeFromProject(employeeId, projectId);
	        return ResponseEntity.ok("Employee removed from project successfully");
	    }
	}


