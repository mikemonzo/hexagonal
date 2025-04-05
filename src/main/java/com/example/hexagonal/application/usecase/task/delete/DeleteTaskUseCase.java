package com.example.hexagonal.application.usecase.task.delete;

import com.example.hexagonal.domain.Task;
import com.example.hexagonal.domain.TaskId;
import com.example.hexagonal.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteTaskUseCase {

    private final TaskRepository taskRepository;

    public void deleteTask(Task task) {
        deleteById(task.getId());

    }

    public void deleteById(TaskId id) {
        taskRepository.deleteById(id);
    }
}
