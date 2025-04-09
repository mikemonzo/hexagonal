package com.example.hexagonal.application.usecase.task.create;

import java.time.LocalDateTime;
import com.example.hexagonal.domain.Task;
import com.example.hexagonal.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateTaskUseCase {
    private final TaskRepository taskRepository;

    public Task create(CreateTaskCommand command) {
        Task task = Task.builder().name(command.title()).description(command.description())
                .createdAt(LocalDateTime.now()).completed(false).author(command.author()).build();
        task = taskRepository.save(task);
        return task;
    }
}
