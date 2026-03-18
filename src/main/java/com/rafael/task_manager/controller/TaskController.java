package com.rafael.task_manager.controller;

import com.rafael.task_manager.domain.Task;
import com.rafael.task_manager.service.task.CreateTaskService;
import com.rafael.task_manager.service.task.SoftDeleteTaskService;
import com.rafael.task_manager.service.task.UpdateTaskService;
import com.rafael.task_manager.service.task.UpdateTaskStatusService;
import com.rafael.task_manager.shared.dto.CreateTaskDTO;
import com.rafael.task_manager.shared.dto.UpdateTaskDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private CreateTaskService createTaskService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UpdateTaskService updateTaskService;
    @Autowired
    private UpdateTaskStatusService updateTaskStatusService;
    @Autowired
    private SoftDeleteTaskService softDeleteTaskService;

    @PostMapping()
    public ResponseEntity<Task> create(@Valid @RequestBody CreateTaskDTO data){
        Task task = this.createTaskService.execute(data);
        return ResponseEntity.ok(task);
    };

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable UUID id, @Valid @RequestBody UpdateTaskDTO data){
        Task task = this.updateTaskService.execute(id, data);
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> updateStatus(@PathVariable UUID id){
        Task task = this.updateTaskStatusService.execute(id);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> softDelete(@PathVariable UUID id){
        Task task = this.softDeleteTaskService.execute(id);
        return ResponseEntity.ok(task);
    }
}
