package com.example.hexagonal.application.usecase.task.edit;

import com.example.hexagonal.domain.TaskId;

public record EditTaskCommand(TaskId id, String title, String description, boolean completed) {

}
