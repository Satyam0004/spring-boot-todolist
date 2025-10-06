package com.kumarsatyam.todolist.controllers;

import com.kumarsatyam.todolist.dtos.TaskDto;
import com.kumarsatyam.todolist.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.createTask(taskDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.updateTask(taskDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDto> deleteTask(@PathVariable Integer id) {
        return ResponseEntity.ok(taskService.deleteTask(id));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<TaskDto> markAsCompleted(@PathVariable Integer id) {
        TaskDto task = taskService.markAsCompleted(id);
        return ResponseEntity.ok(task);
    }

}
