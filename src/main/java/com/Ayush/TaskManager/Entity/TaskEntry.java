package com.Ayush.TaskManager.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "task_Entries")
@Data
public class TaskEntry {

    @Id
    private String id;

    private String task;

}
