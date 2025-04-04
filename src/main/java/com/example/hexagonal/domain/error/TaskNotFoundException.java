package com.example.hexagonal.domain.error;

import com.example.hexagonal.domain.TaskId;

public class TaskNotFoundException extends DomainEntityNotFoundException {

    public TaskNotFoundException() {
        super("task");
    }

    public TaskNotFoundException(TaskId id) {
        super("task", id.getValue().toString());
    }

}
