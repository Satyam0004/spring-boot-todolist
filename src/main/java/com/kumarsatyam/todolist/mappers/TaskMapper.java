package com.kumarsatyam.todolist.mappers;

import com.kumarsatyam.todolist.dtos.TaskDto;
import com.kumarsatyam.todolist.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDto toDto(Task task) {
        if(task == null) return null;

        return new TaskDto(task.getId(), task.getTitle(), task.getStatus(), task.getDueDate());
    }

    public Task toEntity(TaskDto taskDto) {
        if(taskDto == null) return null;
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setStatus(taskDto.getStatus());
        task.setDueDate(taskDto.getDueDate());
        return task;
    }
}
