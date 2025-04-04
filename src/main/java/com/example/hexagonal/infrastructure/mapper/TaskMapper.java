package com.example.hexagonal.infrastructure.mapper;

import com.example.hexagonal.domain.Task;
import com.example.hexagonal.domain.TaskId;
import com.example.hexagonal.infrastructure.db.entity.TaskEntity;

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
}
