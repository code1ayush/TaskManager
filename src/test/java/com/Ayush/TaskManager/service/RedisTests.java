package com.Ayush.TaskManager.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    @Disabled
    public  void redisTesting(){
        redisTemplate.opsForValue().set("email","ayushverma9110@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");
        Assertions.assertEquals("ayushverma9110@gmail.com", (String) email);
    }
}
