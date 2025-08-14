package com.Ayush.TaskManager.Services;

import com.Ayush.TaskManager.Entity.Email;
import com.Ayush.TaskManager.model.Sentiment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SentimentConsumerService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "Foods", groupId = "Foods-group")
    public void consume(String sentimentJson) throws JsonProcessingException {
        Sentiment sentiment = objectMapper.readValue(sentimentJson, Sentiment.class);
        emailService.sendEmail(sentiment);
    }
}
