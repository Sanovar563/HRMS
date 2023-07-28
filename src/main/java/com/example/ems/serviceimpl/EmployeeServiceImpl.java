package com.example.ems.serviceimpl;

import java.util.*;

import java.util.NoSuchElementException;
import javax.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ems.converter.EmployeeConverter;
import com.example.ems.dao.EmployeeRepository;
import com.example.ems.dao.ProjectRepository;
import com.example.ems.dto.EmployeeDTO;
import com.example.ems.dto.ProjectDTO;
import com.example.ems.entities.Employee;
import com.example.ems.entities.Project;
import com.example.ems.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired private EmployeeRepository employeeRepository;
	@Autowired private EmployeeConverter employeeConverter;
	@Autowired 
	private ModelMapper modelmapper;
	@Autowired ProjectRepository projectRepository;


	@Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeConverter.toEntity(employeeDTO);
        Employee createdEmployee = employeeRepository.save(employee);
        return employeeConverter.toDto(createdEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        return employeeConverter.toDto(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(int id, EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        // Update the existing employee entity with the new data
        existingEmployee.setName(employeeDTO.getName());
//        existingEmployee.setUsername(employeeDTO.getUsername());
        existingEmployee.setEmail(employeeDTO.getEmail());
       
        existingEmployee.setSalary(employeeDTO.getSalary());
//        existingEmployee.setLeaveDays(employeeDTO.getLeaveDays());
        existingEmployee.setJoinDate(employeeDTO.getJoinDate());
        existingEmployee.setDepartment(employeeDTO.getDepartment());
        existingEmployee.setPosition(employeeDTO.getPosition());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return employeeConverter.toDto(updatedEmployee);
    }

//    @Override
//    public void deleteEmployee(int id) {
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
//
//        this.employeeRepository.delete(employee);
//    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeConverter.toDto(employees);
    }
    @Override
    public List<ProjectDTO> getProjectsByEmployeeId(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
        List<ProjectDTO> projectDTOs = new ArrayList<>();
        for (Project project : employee.getProjects()) {
            ProjectDTO projectDTO = modelmapper.map(project, ProjectDTO.class);
            projectDTOs.add(projectDTO);
        }
        return projectDTOs;
    }  

    @Override
    public void addEmployeeToProject(int employeeId, int projectId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("Project not found"));
        employee.getProjects().add(project);
        employeeRepository.save(employee);
    }

    @Override
    public void removeEmployeeFromProject(int employeeId, int projectId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("Project not found"));
        employee.getProjects().remove(project);
        employeeRepository.save(employee);
    }
}