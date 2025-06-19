package com.url.shortner.tinyurl.controller;

import com.url.shortner.tinyurl.model.User;
import com.url.shortner.tinyurl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/get-user-details/{id}")
    public User getUserDetails(@PathVariable Long id){
        return userService.getUserDetails(id);
    }

    @GetMapping("/get-user-list")
    public List<User> getUserDetails(){
        return userService.getUserList();
    }

    @PostMapping("/create-new-user")
    public String createNewUser(@RequestBody User user){
        try{
            return userService.createNewUser(user);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    @PutMapping("/update-user")
    public String setUserDetails(@RequestBody User user){
        return userService.setUserDetails(user);
    }
    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id){
        try{
            return userService.deleteUser(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
