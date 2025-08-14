package com.Ayush.TaskManager.repositories;


import com.Ayush.TaskManager.Entity.User;
import com.Ayush.TaskManager.Repositories.UserRepoImp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class UserRepoImpTests {

    @Autowired
    private UserRepoImp userRepoImp;

    @Test
    public void mongoCriteriaTest(){
        Assertions.assertNotNull(userRepoImp.findUser());
    }

    @Test
    public void getUserForSATest(){
        List<User> users = userRepoImp.getUserForSA();
        log.info(users.toString());
        Assertions.assertFalse(users.isEmpty(), "Users list should not be null or empty");

    }

}
