package com.poo2hibernate.educ4all.administrateur;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAdmins(){return adminRepository.findAll();}

    public void addNewAdmin(Admin admin) {
        Optional<Admin> adminByEmail = adminRepository
                .findAdminByEmail(admin.getName());

        if(adminByEmail.isPresent()){
            throw new IllegalStateException("Email Of The Admin already taken");
        }

        adminRepository.save(admin);
    }

    public void deleteAdmin(Long adminId) {
        if(!adminRepository.existsById(adminId)){
            throw new IllegalStateException("This Admin doesn't exist");
        }

        adminRepository.deleteById(adminId);
    }

    @Transactional
    public void updateAdmin(Long adminId, String name, String email, String password) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new IllegalStateException("This Admin doesn't exist"));

        if(name != null && name.length() > 0 && !Objects.equals(name, admin.getName())) {
            admin.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(email, admin.getEmail())) {
            admin.setEmail(email);
        }

        if(password != null && password.length() > 0 && !Objects.equals(password, admin.getPassword())) {
            admin.setPassword(password);
        }

    }

    public boolean verify(String email, String password) {
        Admin admin = adminRepository.findAdminByEmail(email)
                .orElseThrow(() -> new IllegalStateException("This Admin doesn't exist"));

        return admin.getPassword() == password;
    }
}
