package com.Ayush.TaskManager.controller;

import com.Ayush.TaskManager.Entity.User;
import com.Ayush.TaskManager.Services.UserService;
import com.Ayush.TaskManager.Services.WeatherService;
import com.Ayush.TaskManager.api.response.WeatherResponse;
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

    @Autowired
    private WeatherService weatherService;


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

    @GetMapping
    public ResponseEntity<?> greeting(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        String greeting = "";
        WeatherResponse response = weatherService.getWeather("Mumbai");
        if(response!=null){
            greeting = ", feels like " + response.getCurrent().getFeelsLike();
        }
        return new ResponseEntity<>("Hi "+ userName + " temperature here in Mumbai " + greeting,HttpStatus.OK);
    }



}
