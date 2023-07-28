package com.example.ems.service;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.ems.dao.EmployeeRepository;
import com.example.ems.entities.Employee;
import com.example.ems.exception.EntityNotFoundException;

@SpringBootTest
public class ServiceTest {
	
	@MockBean
	private EmployeeRepository employeeRepo;
	
	@Test
	public void testAllEmployee()
	{
		
		Employee dto=new Employee();
		dto.setId(1);
		dto.setName("Sana");
		dto.setEmail("abc@gmail.com");
		dto.setDepartment("hr");
		dto.setPosition("employee");
//		dto.setLeaveDays(2);
		
	
		Employee dto2=new Employee();
		dto2.setId(1);
		dto2.setName("Sana");
		dto2.setEmail("abc@gmail.com");
		dto2.setDepartment("hr");
		dto2.setPosition("employee");
//		dto2.setLeaveDays(2);
		
		List<Employee> li=new ArrayList<>();
		li.add(dto);
		li.add(dto2);
		Mockito.when(this.employeeRepo.findAll()).thenReturn(li);
		//assertThat(this.employeeService.getAllEmployee()).isEqualTo(li);
	}
	
	@Test
	public void testAddEmployee()
	{
		Employee emp=new Employee();
		emp.setId(1);
		emp.setName("Sana");
		emp.setEmail("abc@gmail.com");
		emp.setDepartment("hr");
		emp.setPosition("employee");
//		emp.setLeaveDays(2);
		
		Mockito.when(this.employeeRepo.save(emp)).thenReturn(emp);
		//assertThat(this.employeeService.addEmployee(emp)).isEqualTo(emp);
	}
//	
//	@Test
//	public void testGetById() throws EntityNotFoundException
//	{
//		Employee emp=new Employee();
//		emp.setId(1);
//		emp.setName("Sana");
//		emp.setEmail("abc@gmail.com");
//		emp.setDepartment("hr");
//		emp.setPosition("employee");
//		emp.setLeaveDays(2);
//		
//		Mockito.when(this.employeeRepo.findById(1)).thenReturn(emp);
//		//assertThat(this.employeeService.getById(1)).isEqualTo(emp);
//	}
//	
//	@Test
//	public void testDelete()
//	{
//		Employee emp=new Employee();
//		emp.setEid(1);
//		emp.setEname("Moiz");
//		emp.setEmail("Mazen@gmail.com");
//		emp.setSalary(400000);
//		emp.setDes("xyi");
//		
//		Mockito.when(this.employeeRepo.findById(1)).thenReturn(emp);
//		Mockito.when(this.employeeRepo.existsById(emp.getEid())).thenReturn(false);
//		assertFalse(this.employeeRepo.existsById(emp.getEid()));
//	}
//	
//	@Test
//	public void testUpdateEmployee()
//	{
//		Employee emp=new Employee();
//		emp.setEid(1);
//		emp.setEname("Moiz");
//		emp.setEmail("Mazen@gmail.com");
//		emp.setSalary(400000);
//		emp.setDes("xyi");
//		
//		Mockito.when(this.employeeRepo.findById(1)).thenReturn(emp);
//		emp.setEmail("moizs1234@gmail.com");
//		Mockito.when(this.employeeRepo.save(emp)).thenReturn(emp);
//		
//		//assertThat(this.employeeService.updateEmployee(emp, 1)).isEqualTo(emp);
//	}
//	

}
