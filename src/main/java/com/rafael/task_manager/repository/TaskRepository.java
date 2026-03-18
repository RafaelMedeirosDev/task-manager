package com.rafael.task_manager.repository;

import com.rafael.task_manager.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, String> {

}
