package com.Ayush.TaskManager.controller;

import com.Ayush.TaskManager.Entity.TaskEntry;
import com.Ayush.TaskManager.Entity.User;
import com.Ayush.TaskManager.Services.TaskManagerService;
import com.Ayush.TaskManager.Services.UserService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/taskManager")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskManager {

    @Autowired
    private TaskManagerService taskManagerService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> addTask(@RequestBody TaskEntry taskEntry){
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            taskManagerService.addTask(taskEntry,userName);
            return new ResponseEntity<>(taskEntry, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getTask(){
        try{
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findByUserName(userName);
            List<TaskEntry> taskEntries = user.getTaskEntries();
            return new ResponseEntity<>(taskEntries,HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable String id) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserName(userName);
        List<TaskEntry> collect = user.getTaskEntries().stream().filter(x -> x.getId().equals(id)).toList();
        if (!collect.isEmpty()) {
            Optional<TaskEntry> taskEntry = taskManagerService.getTaskById(id);
            if (taskEntry.isPresent()) {
                return new ResponseEntity<>(taskEntry, HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateTask(@PathVariable String id, @RequestBody TaskEntry updatedTask){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserName(userName);
        List<TaskEntry> collect = user.getTaskEntries().stream().filter(x -> x.getId().equals(id)).toList();
        if (!collect.isEmpty()) {
            Optional<TaskEntry> taskEntry = taskManagerService.getTaskById(id);
            if(taskEntry.isPresent()){
                taskManagerService.updateTask(updatedTask,userName,id);
                return new ResponseEntity<>(updatedTask,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable String id){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserName(userName);
        List<TaskEntry> collect = user.getTaskEntries().stream().filter(x -> x.getId().equals(id)).toList();
        if (!collect.isEmpty()) {
            Optional<TaskEntry>taskEntry = taskManagerService.getTaskById(id);
            if(taskEntry.isPresent()){
                taskManagerService.deleteTask(id,userName);
                return new ResponseEntity<>(true,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);

    }
}
