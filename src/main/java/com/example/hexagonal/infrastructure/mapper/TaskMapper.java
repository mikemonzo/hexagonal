package com.example.hexagonal.infrastructure.mapper;

import com.example.hexagonal.application.usecase.task.create.CreateTaskCommand;
import com.example.hexagonal.application.usecase.task.edit.EditTaskCommand;
import com.example.hexagonal.domain.Task;
import com.example.hexagonal.domain.TaskId;
import com.example.hexagonal.domain.UserId;
import com.example.hexagonal.domain.UserTask;
import com.example.hexagonal.infrastructure.db.entity.TaskEntity;
import com.example.hexagonal.infrastructure.web.dto.task.TaskEditRequest;
import com.example.hexagonal.infrastructure.web.dto.task.TaskRequest;
import com.example.hexagonal.infrastructure.web.dto.task.TaskResponse;
import com.example.hexagonal.infrastructure.web.dto.task.UserTaskResponse;

public class TaskMapper {

    public static TaskEntity toPersistence(Task task) {
        return TaskEntity.builder().id(task.getId() != null ? task.getId().getValue() : null)
                .name(task.getName()).description(task.getDescription())
                .completed(task.isCompleted()).createdAt(task.getCreatedAt())
                .author(task.getAuthor() != null ? task.getAuthor().getValue() : null).build();
    }

    public static Task toDomain(TaskEntity taskEntity) {
        return Task.builder().id(TaskId.of(taskEntity.getId())).name(taskEntity.getName())
                .description(taskEntity.getDescription()).completed(taskEntity.isCompleted())
                .createdAt(taskEntity.getCreatedAt()).author(UserId.of(taskEntity.getAuthor()))
                .build();
    }

    public static CreateTaskCommand toCommand(TaskRequest taskRequest, UserId author) {
        return new CreateTaskCommand(taskRequest.name(), taskRequest.description(), author);
    }

    public static TaskResponse toResponse(Task task) {
        return new TaskResponse(task.getId().getValue(), task.getName(), task.getDescription(),
                task.getCreatedAt(), task.isCompleted(), task.getAuthor());
    }

    public static UserTaskResponse toResponse(UserTask userTask) {
        return new UserTaskResponse(userTask.getTask().getId().getValue(),
                userTask.getTask().getName(), userTask.getTask().getDescription(),
                userTask.getTask().getCreatedAt(), userTask.getTask().isCompleted(),
                userTask.getUser() != null ? UserMapper.toDto(userTask.getUser()) : null);
    }

    public static EditTaskCommand toCommand(Long id, TaskEditRequest taskEditRequest) {
        return new EditTaskCommand(TaskId.of(id), taskEditRequest.name(),
                taskEditRequest.description(), taskEditRequest.completed());
    }
}
