package com.Ayush.TaskManager.controller;

import com.Ayush.TaskManager.Entity.User;
import com.Ayush.TaskManager.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User updatedUser){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User userinDB = userService.findByUserName(userName);
        userinDB.setUserName(updatedUser.getUserName());
        userinDB.setPassword(updatedUser.getPassword());
        userService.createNewUser(userinDB);
        return new ResponseEntity<>(userinDB,HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteUser(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.deleteByUserName(userName);
    }

}
