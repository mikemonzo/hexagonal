package com.example.hexagonal.infrastructure.web.rest.task;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.hexagonal.application.service.CreateTaskService;
import com.example.hexagonal.application.usecase.task.create.CreateTaskCommand;
import com.example.hexagonal.domain.UserTask;
import com.example.hexagonal.infrastructure.mapper.TaskMapper;
import com.example.hexagonal.infrastructure.security.model.AuthUser;
import com.example.hexagonal.infrastructure.web.dto.task.TaskRequest;
import com.example.hexagonal.infrastructure.web.dto.task.UserTaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskPostController {

    private final CreateTaskService createTaskService;

    @PostMapping
    public ResponseEntity<UserTaskResponse> createTask(@RequestBody TaskRequest taskRequest,
            @AuthenticationPrincipal AuthUser user) {
        CreateTaskCommand command = TaskMapper.toCommand(taskRequest, user.getIdAsUserId());
        UserTask userTask = createTaskService.createTask(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(TaskMapper.toResponse(userTask));
    }
}
