package com.Ayush.TaskManager.Services;

import com.Ayush.TaskManager.api.response.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public void setInRedis(String city, WeatherResponse weatherResponse){
        redisTemplate.opsForValue().set(city,weatherResponse);
    }

    public <T> T getFromRedis(String city, Class<T> weatherResponseClass){
        try{
            Object response =  redisTemplate.opsForValue().get(city);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.toString(), weatherResponseClass);
        }catch(Exception e){
            log.error("Error retrieving data from Redis for city: {}", city, e);
            return null;
        }
    }

    public void setInRedis(String city, Object o, Long ttl){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(o); // Convert object to JSON
            redisTemplate.opsForValue().set(city, json, ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Error storing data in Redis for city: {}", city, e);
        }
    }

}
