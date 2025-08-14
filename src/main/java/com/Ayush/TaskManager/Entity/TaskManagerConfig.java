package com.Ayush.TaskManager.Entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "taskManager_Config")
@Data
public class TaskManagerConfig {

    private String key;

    private String value;

}
