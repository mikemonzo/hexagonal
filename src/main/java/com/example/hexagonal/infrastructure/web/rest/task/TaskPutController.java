package com.example.hexagonal.infrastructure.web.rest.task;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.hexagonal.application.usecase.task.edit.CompleteTaskUseCase;
import com.example.hexagonal.application.usecase.task.edit.EditTaskUseCase;
import com.example.hexagonal.domain.TaskId;
import com.example.hexagonal.infrastructure.mapper.TaskMapper;
import com.example.hexagonal.infrastructure.web.dto.task.TaskEditRequest;
import com.example.hexagonal.infrastructure.web.dto.task.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskPutController {

    private final EditTaskUseCase editTaskUseCase;
    private final CompleteTaskUseCase completeTaskUseCase;

    @PutMapping("/{id}/complete")
    public TaskResponse completeTask(@PathVariable Long id) {
        return TaskMapper.toResponse(completeTaskUseCase.completeTask(TaskId.of(id)));
    }

    @PutMapping("/{id}/edit")
    public TaskResponse editTask(@PathVariable Long id,
            @RequestBody TaskEditRequest taskEditRequest) {
        return TaskMapper
                .toResponse(editTaskUseCase.update(TaskMapper.toCommand(id, taskEditRequest)));
    }

}
