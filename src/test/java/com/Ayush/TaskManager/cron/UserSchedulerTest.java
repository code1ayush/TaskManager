package com.Ayush.TaskManager.cron;

import com.Ayush.TaskManager.scheduler.UserScheduler;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulerTest {

    @Autowired
    private UserScheduler userScheduler;

    @Test
    public void testUserScheduler() throws JsonProcessingException {
        userScheduler.userSchedulereMail();
    }
}
