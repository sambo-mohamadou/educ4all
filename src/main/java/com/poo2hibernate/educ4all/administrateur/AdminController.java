package com.poo2hibernate.educ4all.administrateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping(path = "/educ4all/gestion/admin")
public class AdminController {
    private final AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<Admin> getAdmins(){return adminService.getAdmins();}

    @PostMapping(path = "/add")
    public void  registerNewAdmin(@RequestBody Admin admin){adminService.addNewAdmin(admin);}

    @DeleteMapping(path = "/delete/{adminId}")
    public void deleteAdmin(@PathVariable("adminId") String adminId){adminService.deleteAdmin(parseLong(adminId));}

    @PutMapping(path = "/update/{adminId}")
    public void updateAdmin(
            @PathVariable("adminId") String adminId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password){

        adminService.updateAdmin(parseLong(adminId),name,email,password);
    }
    @GetMapping(path = "/verify")
    public boolean verifyPassword(@RequestParam("email") String email, @RequestParam("password") String password){
        return adminService.verify(email,password);
    }

}
