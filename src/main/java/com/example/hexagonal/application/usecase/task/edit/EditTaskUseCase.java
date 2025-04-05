package com.example.hexagonal.application.usecase.task.edit;

import com.example.hexagonal.domain.Task;
import com.example.hexagonal.domain.error.TaskNotFoundException;
import com.example.hexagonal.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EditTaskUseCase {

    private final TaskRepository taskRepository;

    public Task update(EditTaskCommand command) {
        return taskRepository.getById(command.id()).map(task -> {
            task.setName(command.title());
            task.setDescription(command.description());
            task.setCompleted(command.completed());
            return taskRepository.save(task);
        }).orElseThrow(() -> new TaskNotFoundException(command.id()));
    }

}
