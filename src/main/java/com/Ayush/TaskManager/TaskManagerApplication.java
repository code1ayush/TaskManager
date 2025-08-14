package com.Ayush.TaskManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class TaskManagerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(TaskManagerApplication.class, args);
		System.out.println(Arrays.toString(context.getEnvironment().getActiveProfiles()));
	}

	@Bean
	public PlatformTransactionManager anything(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	// used for api fetching
}


// PlatformTransactionManager
// MongoTransactionManager

//8c7ef64d-2084-4b41-b60c-a6b36e671da1