package com.example.hexagonal.application.service;

import com.example.hexagonal.application.usecase.task.create.CreateTaskCommand;
import com.example.hexagonal.application.usecase.task.create.CreateTaskUseCase;
import com.example.hexagonal.application.usecase.user.find.FindUserUseCase;
import com.example.hexagonal.domain.UserTask;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateTaskService {

    private final CreateTaskUseCase createTaskUseCase;
    private final FindUserUseCase findUserUseCase;

    public UserTask createTask(CreateTaskCommand command) {
        var user = findUserUseCase.getById(command.author());
        var task = createTaskUseCase.create(command);
        return UserTask.builder().user(user).task(task).build();
    }
}
