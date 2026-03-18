package com.rafael.task_manager.service.task;

import com.rafael.task_manager.domain.Task;
import com.rafael.task_manager.repository.TaskRepository;
import com.rafael.task_manager.service.CurrentUserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SoftDeleteTaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CurrentUserService currentUser;

    public Task execute(UUID taskId){
        UUID userId = currentUser.getCurrentUserId();

        Task task = taskRepository.findByIdAndUserIdAndDeletedAtIsNull(taskId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found."));

        task.setDeletedAt(LocalDateTime.now());

        return taskRepository.save(task);
    }
}

