package com.Ayush.TaskManager.Repositories;

import com.Ayush.TaskManager.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User,String> {
   User findByUserName(String userName);
   void deleteByUserName(String userName);
}
