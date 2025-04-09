package com.example.hexagonal.application.usecase.task.find;

import java.util.List;
import com.example.hexagonal.domain.Task;
import com.example.hexagonal.domain.TaskId;
import com.example.hexagonal.domain.UserId;
import com.example.hexagonal.domain.error.TaskNotFoundException;
import com.example.hexagonal.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindTaskUseCase {

    private final TaskRepository taskRepository;

    public Task findById(TaskId id) {
        return taskRepository.getById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public List<Task> findAll() {
        List<Task> result = taskRepository.getAll();

        if (result.isEmpty()) {
            throw new TaskNotFoundException();
        }
        return result;
    }

    public List<Task> findAllByUserId(UserId userId) {
        List<Task> result = taskRepository.getAllByUserId(userId);

        if (result.isEmpty()) {
            throw new TaskNotFoundException();
        }
        return result;
    }
}
