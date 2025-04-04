package com.example.hexagonal.domain.repository;

import java.util.List;
import java.util.Optional;
import com.example.hexagonal.domain.Task;
import com.example.hexagonal.domain.TaskId;

public interface TaskRepository {

    Task save(Task task);

    Optional<Task> getById(TaskId id);

    void deleteById(TaskId id);

    List<Task> getAll();

}
