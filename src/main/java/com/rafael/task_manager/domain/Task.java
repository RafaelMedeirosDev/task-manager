package com.rafael.task_manager.domain;

import com.rafael.task_manager.shared.enums.PriorityTypeEnum;
import com.rafael.task_manager.shared.enums.TaskStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "task_number", nullable = false, insertable = false, updatable = false, unique = true)
    private Long taskNumber;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(name = "user_id", nullable = false)
    private UUID userId;
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(columnDefinition = "priority_type", nullable = false)
    private PriorityTypeEnum priority;
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(columnDefinition = "task_status", nullable = false)
    private TaskStatusEnum status;
    @Column(name = "deadline", nullable = false)
    private LocalDate deadLine;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Task(String title, String description, PriorityTypeEnum priority, LocalDate deadLine){
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = TaskStatusEnum.IN_PROGRESS;
        this.deadLine = deadLine;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    public void PrePersist(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if(this.status == null){
            this.status = TaskStatusEnum.IN_PROGRESS;
        }

    }

    @PreUpdate
    public void PreUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
