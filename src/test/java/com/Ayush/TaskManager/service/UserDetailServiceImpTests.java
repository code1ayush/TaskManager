package com.Ayush.TaskManager.service;

import com.Ayush.TaskManager.Entity.User;
import com.Ayush.TaskManager.Repositories.UserRepo;
import com.Ayush.TaskManager.Services.UserDetailServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


//@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("dev")
public class UserDetailServiceImpTests {

//    @Autowired
//    private UserDetailServiceImp userDetailService;
//
//    @MockitoBean
//    private UserRepo userRepo;
//
//    @Test
//    public void testLoadUserByUserName(){
//        when(userRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Ayush").password("random").roles(new ArrayList<>()).build());
//        UserDetails userDetails = userDetailService.loadUserByUsername("Ayush");
//        Assertions.assertNotNull(userDetails);
//    }

    // here we are using MockitoBean cause if only use bean since we are using application context here it will
    // inherit origin userRepo
    // if we want to completely remove dependency from spring context we can use @InjectMocks annotation
    // and replace MockitoBean to Mock but since we are not using spring context here and autowired
    // userRepo will be not because it will not be initialised to remove this error we can use annotation called
    // @ExtendWith(MockitoExtension.class) at the top of the class



    @InjectMocks
    private UserDetailServiceImp userDetailService;

    @Mock
    private UserRepo userRepo;


    @Test
    public void testLoadUserByUserName(){
        when(userRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Ayush").password("random").roles(new ArrayList<>()).build());
        UserDetails userDetails = userDetailService.loadUserByUsername("Ayush");
        Assertions.assertNotNull(userDetails);
    }


}
