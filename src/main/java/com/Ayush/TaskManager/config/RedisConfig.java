package com.Ayush.TaskManager.config;

import com.Ayush.TaskManager.api.response.WeatherResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

//    @Bean
//    public RedisTemplate<String, WeatherResponse> redisTemplate(RedisConnectionFactory factory){
//        RedisTemplate<String, WeatherResponse> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(factory);
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(WeatherResponse.class));
//        return redisTemplate;
//    }
}
