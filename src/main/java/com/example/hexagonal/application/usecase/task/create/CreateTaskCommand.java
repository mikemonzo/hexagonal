package com.example.hexagonal.application.usecase.task.create;

import com.example.hexagonal.domain.UserId;

public record CreateTaskCommand(String name, String description, UserId author) {
}
