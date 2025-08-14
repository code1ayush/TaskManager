package com.Ayush.TaskManager.Repositories;

import com.Ayush.TaskManager.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserRepoImp {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> findUser(){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is("Ayush"));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }

    public List<User> getUserForSA(){
        Query query = new Query();
//        query.addCriteria(Criteria.where("sentiment").ne(null));
//        query.addCriteria(Criteria.where("email").ne(null));

        Criteria criteria = new Criteria();
        query.addCriteria(criteria.andOperator(Criteria.where("sentiment").ne(null),Criteria.where("email").ne(null)));
        List<User> users = mongoTemplate.find(query,User.class);
        return users;
    }
}
