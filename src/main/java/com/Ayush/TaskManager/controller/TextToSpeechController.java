package com.Ayush.TaskManager.controller;


import com.Ayush.TaskManager.Services.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-integration")
@Slf4j
public class TextToSpeechController {

    @Autowired
    private WeatherService weatherService;

//    @GetMapping
//    public void textToSpeech(){
//        try{
//            weatherService.textToSpeech();
//        }catch(Exception e){
//            log.error("Error in textToSpeech: {}", e.getMessage());
//        }
//    }
}
