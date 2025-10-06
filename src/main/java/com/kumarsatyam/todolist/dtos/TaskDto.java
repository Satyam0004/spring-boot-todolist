package com.kumarsatyam.todolist.dtos;

import com.kumarsatyam.todolist.entities.Task.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Integer id;
    private String title;
    private Status status;
    private LocalDate dueDate;
}
