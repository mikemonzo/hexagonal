package com.example.hexagonal.infrastructure.db.repos.impl;

import java.util.List;
import java.util.Optional;
import com.example.hexagonal.domain.Task;
import com.example.hexagonal.domain.TaskId;
import com.example.hexagonal.domain.UserId;
import com.example.hexagonal.domain.repository.TaskRepository;
import com.example.hexagonal.infrastructure.db.entity.TaskEntity;
import com.example.hexagonal.infrastructure.db.repos.jpa.TaskEntityRepositoryJpa;
import com.example.hexagonal.infrastructure.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final TaskEntityRepositoryJpa taskEntityRepositoryJpa;

    @Override
    public Task save(Task task) {
        TaskEntity taskEntity = TaskMapper.toPersistence(task);
        return TaskMapper.toDomain(taskEntityRepositoryJpa.save(taskEntity));
    }

    @Override
    public Optional<Task> getById(TaskId id) {
        return taskEntityRepositoryJpa.findById(id.getValue()).map(TaskMapper::toDomain);
    }

    @Override
    public void deleteById(TaskId id) {
        taskEntityRepositoryJpa.deleteById(id.getValue());
    }

    @Override
    public List<Task> getAll() {
        return taskEntityRepositoryJpa.findAll().stream().map(TaskMapper::toDomain).toList();
    }

    @Override
    public List<Task> getAllByUserId(UserId userId) {
        return taskEntityRepositoryJpa.findAllByAuthor(userId.getValue()).stream()
                .map(TaskMapper::toDomain).toList();
    }

}
