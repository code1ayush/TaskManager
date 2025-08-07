package com.Ayush.TaskManager.controller;

import com.Ayush.TaskManager.Entity.User;
import com.Ayush.TaskManager.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String healthCheck(){
        return "Health check is good";
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        try{
            userService.createNewUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
