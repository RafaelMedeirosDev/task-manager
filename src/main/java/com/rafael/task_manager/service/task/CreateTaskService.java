package com.rafael.task_manager.service.task;

import com.rafael.task_manager.domain.Credential;
import com.rafael.task_manager.domain.Task;
import com.rafael.task_manager.domain.User;
import com.rafael.task_manager.repository.TaskRepository;
import com.rafael.task_manager.repository.UserRepository;
import com.rafael.task_manager.shared.dto.CreateTaskDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public Task execute(CreateTaskDTO data){
        Credential credential = (Credential) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userRepository.getByCredentialId(credential.getId())
                .orElseThrow(() -> new EntityNotFoundException("Authenticated user not found."));

        Task task = new Task(data.title(), data.description(), data.priority(), data.deadline());

        task.setUserId(user.getId());

        return this.taskRepository.save(task);
    }
}
