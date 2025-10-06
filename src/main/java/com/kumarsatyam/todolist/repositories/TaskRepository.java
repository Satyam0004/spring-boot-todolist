package com.kumarsatyam.todolist.repositories;

import com.kumarsatyam.todolist.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository <Task, Integer> {
}
