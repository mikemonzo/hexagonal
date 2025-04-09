package com.example.hexagonal.infrastructure.web.rest.admin;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.hexagonal.application.service.FindTaskService;
import com.example.hexagonal.infrastructure.mapper.TaskMapper;
import com.example.hexagonal.infrastructure.mapper.UserMapper;
import com.example.hexagonal.infrastructure.web.dto.task.UserTaskResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/task")
@RequiredArgsConstructor
public class AdminTaskGetController {

    private final FindTaskService findTaskService;

    public List<UserTaskResponse> allTasks() {
        return findTaskService.findAllTasks().stream().map(TaskMapper::toResponse).toList();
    }
}
