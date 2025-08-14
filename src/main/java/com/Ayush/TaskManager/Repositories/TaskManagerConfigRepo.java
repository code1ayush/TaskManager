package com.Ayush.TaskManager.Repositories;

import com.Ayush.TaskManager.Entity.TaskManagerConfig;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskManagerConfigRepo extends MongoRepository<TaskManagerConfig, ObjectId> {

}
