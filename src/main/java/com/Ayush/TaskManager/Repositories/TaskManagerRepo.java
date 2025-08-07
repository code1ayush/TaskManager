package com.Ayush.TaskManager.Repositories;

import com.Ayush.TaskManager.Entity.TaskEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskManagerRepo extends MongoRepository<TaskEntry,String> {
}
