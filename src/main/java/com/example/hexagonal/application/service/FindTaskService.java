package com.example.hexagonal.application.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.example.hexagonal.application.usecase.task.find.FindTaskUseCase;
import com.example.hexagonal.application.usecase.user.find.FindUserUseCase;
import com.example.hexagonal.domain.Task;
import com.example.hexagonal.domain.TaskId;
import com.example.hexagonal.domain.User;
import com.example.hexagonal.domain.UserId;
import com.example.hexagonal.domain.UserTask;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindTaskService {

    private final FindTaskUseCase findTaskUseCase;
    private final FindUserUseCase findUserUseCase;

    public UserTask findTaskById(TaskId taskId) {
        // Use the findTaskUseCase to find a task by its ID
        var task = findTaskUseCase.findById(taskId);
        var user = findUserUseCase.getById(task.getAuthor());

        return UserTask.builder().task(task).user(user).build();
    }

    public List<UserTask> findAllTasks() {
        List<Task> tasks = findTaskUseCase.findAll();
        List<UserId> userIds =
                tasks.stream().map(Task::getAuthor).distinct().collect(Collectors.toList());

        Map<UserId, List<User>> users = findUserUseCase.getByIds(userIds).stream()
                .collect(Collectors.groupingBy(User::getId));
        return tasks.stream()
                .map(task -> UserTask.builder()
                        .task(task)
                        .user(users.get(task.getAuthor()).get(0))
                        .build())
                .collect(Collectors.toList());
    }
}
