package com.example.hexagonal.application.usecase.task.edit;

import com.example.hexagonal.domain.Task;
import com.example.hexagonal.domain.TaskId;
import com.example.hexagonal.domain.error.TaskNotFoundException;
import com.example.hexagonal.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompleteTaskUseCase {

    private final TaskRepository taskRepository;

    public Task completeTask(Task task) {
        task.taskCompleted();
        return taskRepository.save(task);
    }

    public Task completeTask(TaskId id) {
        return taskRepository.getById(id).map(task -> {
            task.taskCompleted();
            return taskRepository.save(task);
        }).orElseThrow(() -> new TaskNotFoundException(id));
    }
}
