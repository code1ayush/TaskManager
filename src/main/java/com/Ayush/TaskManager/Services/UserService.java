package com.Ayush.TaskManager.Services;

import com.Ayush.TaskManager.Entity.User;
import com.Ayush.TaskManager.Repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j // This annotation will create a logger instance for us
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
//    insted of using the above line, we can use slf4j annotation to create a logger instance


    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public void createUsers(User user){
        userRepo.save(user);
    }

    public boolean createNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(List.of("User"));
            userRepo.save(user);
            return true;
        }catch(Exception e){
            log.error("Error creating new user {}",user.getUserName(),e); // instead of using logger we can use log.error
            log.info("info creating new user:");
            log.warn("warning creating new user");
            log.debug("debug creating new user");
            log.trace("trace creating new user");
            return false;
        }
    }

    public void CreateAdminUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("User","ADMIN"));
        userRepo.save(user);
    }

    public void updateUser(User updatedUser){
        userRepo.save(updatedUser);
    }

    public User findByUserName(String userName){
        return userRepo.findByUserName(userName);
    }

    public void deleteByUserName(String userName){
        userRepo.deleteByUserName(userName);
    }

}
