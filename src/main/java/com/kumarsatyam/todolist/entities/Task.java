package com.kumarsatyam.todolist.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String Title;

    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate dueDate;

    public enum Status {
        PENDING, COMPLETED
    }
}
