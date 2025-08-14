package com.Ayush.TaskManager.scheduler;

import com.Ayush.TaskManager.Entity.User;
import com.Ayush.TaskManager.Repositories.UserRepoImp;
import com.Ayush.TaskManager.Services.EmailService;
import com.Ayush.TaskManager.Services.SentimentAnalysisService;
import com.Ayush.TaskManager.model.Sentiment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserScheduler {

    @Autowired
    private UserRepoImp userRepoImp;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Scheduled(cron = "0 0 9 ? * SUN")
    public void userSchedulereMail() throws JsonProcessingException {
        List<User> users = userRepoImp.getUserForSA();
        for(User user : users){
            String taskEntriesofUser = user.getTaskEntries().toString();
            Sentiment sentiment = Sentiment.builder().email(user.getEmail()).sentiment(taskEntriesofUser).subject("This is an auomated email").build();
            if(sentiment!= null){
                String json = objectMapper.writeValueAsString(sentiment);
                kafkaTemplate.send("Foods",sentiment.getEmail(), json);
            }
        }
    }
}
