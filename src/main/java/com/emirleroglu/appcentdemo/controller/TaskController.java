package com.emirleroglu.appcentdemo.controller;

import com.emirleroglu.appcentdemo.model.Task;
import com.emirleroglu.appcentdemo.payload.response.MessageResponse;
import com.emirleroglu.appcentdemo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    TaskRepository taskRepository;


    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        try {
            taskRepository.save(task);
            return ResponseEntity.ok(new MessageResponse("Task created successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("oops something is wrong"));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTaskById(@RequestParam long id) {
        try {
            taskRepository.deleteById(id);
            return ResponseEntity.ok(new MessageResponse("Task deleted !!!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("oops something is wrong"));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTaskById(@RequestParam long id, @RequestBody Task task) {
        try {
            Task updateTask = taskRepository.findById(id).get();
            updateTask.setUser(task.getUser());
            updateTask.setDescription(task.getDescription());
            updateTask.setComplated(task.getComplated());
            updateTask.setTaskDate(task.getTaskDate());
            taskRepository.save(updateTask);
            return ResponseEntity.ok(new MessageResponse("Task updated !!!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("oops something is wrong"));
        }
    }

    @GetMapping("/getTasks")
    public List<Task> getAllTasksByAppUSerId(@RequestParam long userId) {
        return taskRepository.findAllByUserId(userId);
    }


}
