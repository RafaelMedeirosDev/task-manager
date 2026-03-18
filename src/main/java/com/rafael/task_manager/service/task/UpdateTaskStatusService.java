package com.rafael.task_manager.service.task;

import com.rafael.task_manager.domain.Task;
import com.rafael.task_manager.repository.TaskRepository;
import com.rafael.task_manager.service.CurrentUserService;
import com.rafael.task_manager.shared.enums.TaskStatusEnum;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateTaskStatusService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CurrentUserService currentUserService;

    public Task execute(UUID taskId){
        UUID userId = currentUserService.getCurrentUserId();

        Task task = taskRepository.findByIdAndUserIdAndDeletedAtIsNull(taskId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found."));

        task.setStatus(TaskStatusEnum.COMPLETED);

        return taskRepository.save(task);
    }
}
