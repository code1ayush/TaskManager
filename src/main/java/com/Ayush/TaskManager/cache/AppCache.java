package com.Ayush.TaskManager.cache;


import com.Ayush.TaskManager.Entity.TaskManagerConfig;
import com.Ayush.TaskManager.Repositories.TaskManagerConfigRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private TaskManagerConfigRepo taskManagerConfigRepo;

    public Map<String, String> App_Cache = new HashMap<>();

    @PostConstruct
    public void init(){
        App_Cache = new HashMap<>();
        List<TaskManagerConfig> all = taskManagerConfigRepo.findAll();
        for(TaskManagerConfig taskManagerConfig:all){
            App_Cache.put(taskManagerConfig.getKey(),taskManagerConfig.getValue());
        }
    }

}
