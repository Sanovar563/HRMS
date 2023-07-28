//package com.example.ems.serviceimpl;
//
//import java.util.List;
//
//import javax.persistence.EntityNotFoundException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.ems.converter.AdminConverter;
//import com.example.ems.dao.AdminRepository;
//import com.example.ems.dto.AdminDTO;
//import com.example.ems.entities.Admin;
//import com.example.ems.exception.ResourceNotFoundException;
//import com.example.ems.service.AdminService;
//
//@Service
//public class AdminServiceImpl implements AdminService {
//	@Autowired private AdminRepository adminRepository;
//	@Autowired AdminConverter adminConverter;
//
//	
//	//methods overriding from service interface
//    @Override
//    public AdminDTO createAdmin(AdminDTO adminDTO) {
//        Admin admin = adminConverter.toEntity(adminDTO);
//        Admin createdAdmin = adminRepository.save(admin);
//        return adminConverter.toDto(createdAdmin);
//    }
//
//    @Override
//    public AdminDTO getAdminById(int id) {
//        Admin admin = adminRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
//        return adminConverter.toDto(admin);
//    }
//
//    @Override
//    public AdminDTO updateAdmin(int id, AdminDTO adminDTO) {
//        Admin existingAdmin = adminRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
//
//        // Update the existing admin entity with the new data
//        existingAdmin.setUsername(adminDTO.getUsername());
//        existingAdmin.setEmail(adminDTO.getEmail());
//        existingAdmin.setPassword(adminDTO.getPassword());
//        Admin updatedAdmin = adminRepository.save(existingAdmin);
//        return adminConverter.toDto(updatedAdmin);
//    }
//
//    @Override
//    public void deleteAdmin(int id) {
//        Admin admin = adminRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Admin","Id", id));
//this.adminRepository.delete(admin);
//        adminRepository.delete(admin);
//    }
//
//    @Override
//    public List<AdminDTO> getAllAdmins() {
//        List<Admin> admins = adminRepository.findAll();
//        return adminConverter.toDto(admins);
//    }
//}