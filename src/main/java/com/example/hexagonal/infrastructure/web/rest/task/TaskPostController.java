package com.example.hexagonal.infrastructure.web.rest.task;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.hexagonal.application.usecase.task.create.CreateTaskCommand;
import com.example.hexagonal.application.usecase.task.create.CreateTaskUseCase;
import com.example.hexagonal.domain.Task;
import com.example.hexagonal.infrastructure.mapper.TaskMapper;
import com.example.hexagonal.infrastructure.web.dto.TaskRequest;
import com.example.hexagonal.infrastructure.web.dto.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskPostController {

    private final CreateTaskUseCase createTaskUseCase;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest) {
        CreateTaskCommand command = TaskMapper.toCommand(taskRequest);
        Task task = createTaskUseCase.create(command);
        TaskResponse taskResponse = TaskMapper.toResponse(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponse);
    }
}
