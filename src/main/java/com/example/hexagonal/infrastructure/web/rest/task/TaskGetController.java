package com.example.hexagonal.infrastructure.web.rest.task;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.hexagonal.application.usecase.task.find.FindTaskUseCase;
import com.example.hexagonal.domain.Task;
import com.example.hexagonal.domain.TaskId;
import com.example.hexagonal.infrastructure.mapper.TaskMapper;
import com.example.hexagonal.infrastructure.security.model.AuthUser;
import com.example.hexagonal.infrastructure.web.dto.task.TaskResponse;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskGetController {

    private final FindTaskUseCase findTaskUseCase;

    @GetMapping
    public List<TaskResponse> allTasks(@AuthenticationPrincipal AuthUser user) {
        return findTaskUseCase.findAllByUserId(user.getIdAsUserId()).stream()
                .map(TaskMapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        Task task = findTaskUseCase.findById(TaskId.of(id));
        return TaskMapper.toResponse(task);
    }
}
