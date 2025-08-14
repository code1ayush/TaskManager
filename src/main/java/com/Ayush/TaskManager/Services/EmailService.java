package com.Ayush.TaskManager.Services;

import com.Ayush.TaskManager.model.Sentiment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendEmail(Sentiment sentiment){
        try{
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(sentiment.getEmail());
            mail.setSubject(sentiment.getSubject());
            mail.setText(sentiment.getSentiment());
            javaMailSender.send(mail);
        }catch (Exception e){
            log.error("Error is sending email {}",sentiment.getEmail(),e);
        }
    }

}
