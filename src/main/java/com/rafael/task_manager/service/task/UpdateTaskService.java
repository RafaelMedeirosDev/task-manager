package com.rafael.task_manager.service.task;

import com.rafael.task_manager.domain.Task;
import com.rafael.task_manager.repository.TaskRepository;
import com.rafael.task_manager.service.CurrentUserService;
import com.rafael.task_manager.shared.dto.UpdateTaskDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateTaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CurrentUserService currentUserService;

    public Task execute(UUID taskId, UpdateTaskDTO data){
        UUID userId = currentUserService.getCurrentUserId();

        Task task = taskRepository.findByIdAndUserIdAndDeletedAtIsNull(taskId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found."));

        task.setTitle(data.title());
        task.setDescription(data.description());
        task.setPriority(data.priority());
        task.setDeadLine(data.deadline());

        return taskRepository.save(task);

    }
}
