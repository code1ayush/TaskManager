package com.Ayush.TaskManager.Services;

import com.Ayush.TaskManager.Entity.TaskEntry;
import com.Ayush.TaskManager.Entity.User;
import com.Ayush.TaskManager.Repositories.TaskManagerRepo;
import com.Ayush.TaskManager.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class TaskManagerService {

    @Autowired
    private TaskManagerRepo taskManagerRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Transactional
    public void addTask(TaskEntry taskEntry,String userName){
        try{
            User user = userService.findByUserName(userName);
            TaskEntry saved = taskManagerRepo.save(taskEntry);
                user.getTaskEntries().removeIf(t -> t.getId().equals(saved.getId()));
                user.getTaskEntries().add(saved);
                userService.createUsers(user);

        }catch (Exception e){
            throw new RuntimeException("an error occurred while saving the task");
        }
    }

    public List<TaskEntry> getTask(){
        return taskManagerRepo.findAll();
    }

    public Optional<TaskEntry> getTaskById(String id){
        return taskManagerRepo.findById(id);
    }

    public void updateTask(TaskEntry updatedTask, String userName,String id){
        User user = userRepo.findByUserName(userName);
        updatedTask.setId(id);
        user.getTaskEntries().removeIf(t -> t.getId().equals(id));
        taskManagerRepo.save(updatedTask);
    }

    @Transactional
    public void deleteTask(String id,String userName){
        User user = userRepo.findByUserName(userName);
        user.getTaskEntries().removeIf(x->x.getId().equals(id));
        userService.createUsers(user);
        taskManagerRepo.deleteById(id);
    }
}
