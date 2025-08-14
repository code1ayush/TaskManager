package com.Ayush.TaskManager.controller;


import com.Ayush.TaskManager.Entity.User;
import com.Ayush.TaskManager.Services.UserService;
import com.Ayush.TaskManager.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppCache appCache;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User>all = userService.getAllUsers();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/Create-admin-user")
    public ResponseEntity<?> createAdminUser(@RequestBody User user){
        try{
            userService.CreateAdminUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("clear-app-cache")
    public void clearCache(){
        appCache.init();
    }
}

