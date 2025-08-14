package com.Ayush.TaskManager.controller;

import com.Ayush.TaskManager.Entity.User;
import com.Ayush.TaskManager.Services.UserDetailServiceImp;
import com.Ayush.TaskManager.Services.UserService;
import com.Ayush.TaskManager.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImp userDetailServiceImp;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "Health check is good";
    }

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody User user){
        try{
            userService.createNewUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody User user){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));// Authenticate the user
            UserDetails userDetails = userDetailServiceImp.loadUserByUsername(user.getUserName());
            String jwt = jwtUtils.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);

        }catch (Exception e){
            log.error("Exception occurred during login for user {}",user.getUserName(), e);
            return new ResponseEntity<>("Incorrect userName/Password", HttpStatus.UNAUTHORIZED);
        }
    }

}
