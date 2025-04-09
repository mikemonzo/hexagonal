package com.example.hexagonal.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.hexagonal.application.service.CreateTaskService;
import com.example.hexagonal.application.service.FindTaskService;
import com.example.hexagonal.application.usecase.task.create.CreateTaskUseCase;
import com.example.hexagonal.application.usecase.task.delete.DeleteTaskUseCase;
import com.example.hexagonal.application.usecase.task.edit.CompleteTaskUseCase;
import com.example.hexagonal.application.usecase.task.edit.EditTaskUseCase;
import com.example.hexagonal.application.usecase.task.find.FindTaskUseCase;
import com.example.hexagonal.application.usecase.user.find.FindUserUseCase;
import com.example.hexagonal.domain.repository.TaskRepository;
import com.example.hexagonal.infrastructure.db.repos.impl.TaskRepositoryImpl;
import com.example.hexagonal.infrastructure.db.repos.jpa.TaskEntityRepositoryJpa;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class TaskConfig {

    private final TaskEntityRepositoryJpa taskEntityRepositoryJpa;
    private final FindUserUseCase findUserUseCase;

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

    @Bean
    public CompleteTaskUseCase completeTaskUseCase() {
        return new CompleteTaskUseCase(taskRepositoryJpa());
    }

    @Bean
    public EditTaskUseCase editTaskUseCase() {
        return new EditTaskUseCase(taskRepositoryJpa());
    }

    @Bean
    public DeleteTaskUseCase deleteTaskUseCase() {
        return new DeleteTaskUseCase(taskRepositoryJpa());
    }

    @Bean
    public CreateTaskService createTaskService() {
        return new CreateTaskService(createTaskUseCase(), findUserUseCase);
    }

    @Bean
    public FindTaskService findTaskService() {
        return new FindTaskService(findTaskUseCase(), findUserUseCase);
    }
}
