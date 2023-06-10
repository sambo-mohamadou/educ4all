package com.poo2hibernate.educ4all.user;

import com.poo2hibernate.educ4all.niveau.Niveau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping(path = "/educ4all")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/gestion/user")
    public List<User> getUsers(){return userService.getUsers();}

    @PostMapping(path = "/gestion/user/add")
    public void  registerNewUser(@RequestBody User user){userService.addNewUser(user);}

    @DeleteMapping(path = "/gestion/user/delete/{userId}")
    public void deleteUser(@PathVariable("userId") String userId){userService.deleteUser(parseLong(userId));}

    @PutMapping(path = "/gestion/user/update/{userId}")
    public void updateUser(String userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) Niveau niveau){

        userService.updateUser(parseLong(userId),name,email,password,niveau);
    }

    @GetMapping(path = "user/verify")
    public boolean verifyPassword(@RequestParam("email") String email, @RequestParam("password") String password){
        return userService.verify(email,password);
    }
}
