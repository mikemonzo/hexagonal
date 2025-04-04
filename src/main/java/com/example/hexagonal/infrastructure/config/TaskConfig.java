package com.example.hexagonal.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.hexagonal.application.usecase.task.create.CreateTaskUseCase;
import com.example.hexagonal.application.usecase.task.find.FindTaskUseCase;
import com.example.hexagonal.domain.repository.TaskRepository;
import com.example.hexagonal.infrastructure.db.repos.impl.TaskRepositoryImpl;
import com.example.hexagonal.infrastructure.db.repos.jpa.TaskEntityRepositoryJpa;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class TaskConfig {

    private final TaskEntityRepositoryJpa taskEntityRepositoryJpa;

    @Bean
    public TaskRepository taskRepositoryJpa() {
        return new TaskRepositoryImpl(taskEntityRepositoryJpa);
    }

    @Bean
    public CreateTaskUseCase createTaskUseCase() {
        return new CreateTaskUseCase(taskRepositoryJpa());
    }

    @Bean
    public FindTaskUseCase findTaskUseCase() {
        return new FindTaskUseCase(taskRepositoryJpa());
    }

}
