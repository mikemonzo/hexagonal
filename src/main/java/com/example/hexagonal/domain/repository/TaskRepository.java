package com.example.hexagonal.domain.repository;

import java.util.List;
import com.example.hexagonal.domain.Task;
import com.example.hexagonal.domain.TaskId;

public interface TaskRepository {

    void save(Task task);

    Task getById(TaskId id);

    void deleteById(TaskId id);

    List<Task> getAll();

}
