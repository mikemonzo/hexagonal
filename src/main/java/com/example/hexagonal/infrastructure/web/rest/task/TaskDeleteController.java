package com.example.hexagonal.infrastructure.web.rest.task;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.hexagonal.application.usecase.task.delete.DeleteTaskUseCase;
import com.example.hexagonal.domain.TaskId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskDeleteController {

    private final DeleteTaskUseCase deleteTaskUseCase;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable Long id) {
        deleteTaskUseCase.deleteById(TaskId.of(id));
        return ResponseEntity.noContent().build();
    }
}
