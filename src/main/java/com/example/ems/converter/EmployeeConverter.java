package com.example.ems.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ems.dto.EmployeeDTO;
import com.example.ems.entities.Employee;



@Component
public class EmployeeConverter {
	@Autowired 
	private ModelMapper modelmapper;
	  public EmployeeDTO toDto(Employee employee) {
	        EmployeeDTO dto =this.modelmapper.map(employee, EmployeeDTO.class);
//        dto.setId(employee.getId());
//        dto.setName(employee.getName());
//        dto.setUsername(employee.getUsername());
//        dto.setEmail(employee.getEmail());
//        dto.setSalary(employee.getSalary());
//        dto.setLeave(employee.getLeave());
//        dto.setJoinDate(employee.getJoinDate());
//        dto.setDepartment(employee.getDepartment());
//        dto.setPosition(employee.getPosition());
        return dto;
    }
	  public List<EmployeeDTO> toDto(List<Employee> employee)
		{
			return employee.stream().map(x-> toDto(x)).collect(Collectors.toList());
		}

	  public Employee toEntity(EmployeeDTO dto) {
	        Employee employee = this.modelmapper.map(dto, Employee.class);
//        employee.setId(dto.getId());
//        employee.setName(dto.getName());
//        employee.setUsername(dto.getUsername());
//        employee.setEmail(dto.getEmail());
//        employee.setSalary(dto.getSalary());
//        employee.setLeave(dto.getLeave());
//        employee.setJoinDate(dto.getJoinDate());
//        employee.setDepartment(dto.getDepartment());
//        employee.setPosition(dto.getPosition());
        return employee;
    }
	  public List<Employee> toEntity(List<EmployeeDTO> dto)
		{
			return dto.stream().map(x->toEntity(x)).collect(Collectors.toList());
		}
}
