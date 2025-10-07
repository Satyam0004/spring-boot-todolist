package com.kumarsatyam.todolist.services;

import com.kumarsatyam.todolist.dtos.TaskDto;
import com.kumarsatyam.todolist.entities.Task;
import com.kumarsatyam.todolist.mappers.TaskMapper;
import com.kumarsatyam.todolist.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskDto createTask(TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task saved = taskRepository.save(task);
        return taskMapper.toDto(saved);
    }

    public TaskDto updateTask(TaskDto taskDto) {
        if (taskDto.getId() == null) {
            throw new RuntimeException("Task ID is required for update");
        }

        Task existingTask = taskRepository.findById(taskDto.getId())
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Update only the fields from DTO
        existingTask.setTitle(taskDto.getTitle());
        existingTask.setStatus(taskDto.getStatus());
        existingTask.setDueDate(taskDto.getDueDate());

        Task saved = taskRepository.save(existingTask);
        return taskMapper.toDto(saved);
    }

    public TaskDto deleteTask(Integer id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepository.delete(task);

        return taskMapper.toDto(task);
    }

    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    public TaskDto markAsCompleted(Integer id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus(Task.Status.COMPLETED);
        Task saved = taskRepository.save(task);

        return taskMapper.toDto(saved);
    }
}
