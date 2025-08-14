package com.Ayush.TaskManager.controller;

import com.Ayush.TaskManager.Entity.Email;
import com.Ayush.TaskManager.Services.EmailService;
import com.Ayush.TaskManager.Services.UserService;
import com.Ayush.TaskManager.scheduler.UserScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("send-email")
public class SendEmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserScheduler userScheduler;

//    @PostMapping
//    public ResponseEntity<?> sendEmail(@RequestBody Email email){
//        try{
//            emailService.sendEmail(email);
////            userScheduler.userSchedulereMail();
//            return new ResponseEntity<>(HttpStatus.OK);
//        }catch(Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
}
