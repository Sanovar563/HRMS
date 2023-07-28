package com.example.ems.controller;

import java.util.List;




import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ems.dao.EmployeeRepository;
import com.example.ems.dto.EmployeeDTO;
import com.example.ems.dto.HRDTO;
import com.example.ems.exception.EntityNotFoundException;
import com.example.ems.service.HRService;

@RestController
@RequestMapping("/hr")
@CrossOrigin("*")
public class HRController {

   
    @Autowired HRService hrService ;
    @Autowired EmployeeRepository employeeRepository;
    
//    
//    @PostMapping("/login")
//    public ResponseEntity<Boolean> login(@RequestParam String username, @RequestParam String password) {
//        boolean loginSuccessful = hrService.login(username, password);
//        if (loginSuccessful) {
//            return ResponseEntity.ok(true);
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
//    }
    @PostMapping("/")
    public ResponseEntity<HRDTO> createHRProfile(@Valid @RequestBody HRDTO hrDTO) {
        HRDTO createdHR = hrService.createHRProfile(hrDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHR);
    }
    @PutMapping("/profile")
    public ResponseEntity<Boolean> updateHRProfile(@RequestBody HRDTO hrDTO) {
        boolean updated = hrService.updateHRProfile(hrDTO);
        if (updated) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build(); 
    }

    @GetMapping("/profile/{hrId}")
    public ResponseEntity<HRDTO> getHRProfileById(@PathVariable Long hrId) {
        HRDTO hrProfile = hrService.getHRProfileById(hrId);
        if (hrProfile != null) {
            return ResponseEntity.ok(hrProfile);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/profiles")
    public ResponseEntity<List<HRDTO>> getAllHRProfiles() {
        List<HRDTO> hrProfiles = hrService.getAllHRProfiles();
        if (!hrProfiles.isEmpty()) {
            return ResponseEntity.ok(hrProfiles);
        }
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/employees")
    public ResponseEntity<EmployeeDTO> createEmployeeProfile(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = hrService.createEmployee(employeeDTO);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

//    @PutMapping("/employees")
//    public ResponseEntity<Boolean> updateEmployeeProfile(@RequestBody EmployeeDTO employeeDTO) {
//        boolean updated = hrService.updateEmployee(employeeDTO);
//        if (updated) {
//            return new ResponseEntity<>(true, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
//    }
    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable int id, @RequestBody EmployeeDTO employeeDTO) {
        try {
            EmployeeDTO updatedEmployee = hrService.updateEmployee(id, employeeDTO);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int employeeId) {
        EmployeeDTO employee = hrService.getEmployeeById(employeeId);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }  
    @PutMapping("/employees/{employeeId}/disable")
    public ResponseEntity<Boolean> disableEmployeeProfile(@PathVariable String employeeId) {
        boolean disabled = hrService.disableEmployeeProfile(employeeId);
        if (disabled) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
    @PutMapping("/employees/{employeeId}/enable")
    public ResponseEntity<Boolean> enableEmployeeProfile(@PathVariable String employeeId) {
        boolean enabled = hrService.enableEmployeeProfile(employeeId);
        if (enabled) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
    @GetMapping("/employees/{employeeId}/disabled")
    public ResponseEntity<Boolean> isEmployeeProfileDisabled(@PathVariable String employeeId) {
        boolean isDisabled = hrService.isEmployeeProfileDisabled(employeeId);
        return new ResponseEntity<>(isDisabled, HttpStatus.OK);
    }
}
