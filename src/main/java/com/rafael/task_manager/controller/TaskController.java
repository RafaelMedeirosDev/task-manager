package com.rafael.task_manager.controller;

import com.rafael.task_manager.domain.Task;
import com.rafael.task_manager.service.task.CreateTaskService;
import com.rafael.task_manager.shared.dto.CreateTaskDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private CreateTaskService createTaskService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping()
    public ResponseEntity<Task> create(@Valid @RequestBody CreateTaskDTO data){
        Task task = this.createTaskService.execute(data);
        return ResponseEntity.ok(task);
    };
}
