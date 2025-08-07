package com.Ayush.TaskManager.service;

import com.Ayush.TaskManager.Entity.User;
import com.Ayush.TaskManager.Repositories.UserRepo;
import com.Ayush.TaskManager.Services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;


@SpringBootTest
public class UserServiceTests{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Disabled
    @Test
    public void testAdd(){
        assertEquals("some should be 4",4,2+2);
    }

    @ParameterizedTest
    @ValueSource(strings ={
            "Ayush",
            "admin",
            "aish"
    })
    public void testFindByUserName(String user){
        assertNotNull("This should be not null",userRepo.findByUserName(user));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,4",
            "3,3,6"
    })
    public void test(int a, int b, int Expected){
        assertEquals("This is a parameterized test for ",Expected,a+b);
    }

    @ParameterizedTest
    @Disabled
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testCreateNewUser(User user){
        assertTrue(userService.createNewUser(user));
    }


//    @BeforeAll
//    @BeforeEach



}
