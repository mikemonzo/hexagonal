package com.example.hexagonal.infrastructure.mapper;

import com.example.hexagonal.application.usecase.task.create.CreateTaskCommand;
import com.example.hexagonal.domain.Task;
import com.example.hexagonal.domain.TaskId;
import com.example.hexagonal.infrastructure.db.entity.TaskEntity;
import com.example.hexagonal.infrastructure.web.dto.TaskRequest;
import com.example.hexagonal.infrastructure.web.dto.TaskResponse;

public class TaskMapper {

    public static TaskEntity toPersistence(Task task) {
        return TaskEntity.builder().id(task.getId() != null ? task.getId().getValue() : null)
                .name(task.getName()).description(task.getDescription())
                .completed(task.isCompleted()).createdAt(task.getCreatedAt()).build();
    }

    public static Task toDomain(TaskEntity taskEntity) {
        return Task.builder().id(TaskId.of(taskEntity.getId())).name(taskEntity.getName())
                .description(taskEntity.getDescription()).completed(taskEntity.isCompleted())
                .createdAt(taskEntity.getCreatedAt()).build();
    }

    public static CreateTaskCommand toCommand(TaskRequest taskRequest) {
        return new CreateTaskCommand(taskRequest.name(), taskRequest.description());
    }

    public static TaskResponse toResponse(Task task) {
        return new TaskResponse(task.getId().getValue(), task.getName(), task.getDescription(),
                task.getCreatedAt(), task.isCompleted());
    }
}
