package com.example.hexagonal.domain;

import lombok.Value;

@Value
public class TaskId {

    Long value;

    public static TaskId of(Long value) {
        return new TaskId(value);
    }
}
