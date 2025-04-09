package com.example.hexagonal.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.AccessLevel;

@Data
@AllArgsConstructor
@Builder
public class Task {

    @Setter(AccessLevel.NONE)
    private TaskId id;
    private String name;
    private String description;
    private boolean completed;
    private LocalDateTime createdAt;
    private UserId author;

    public void taskCompleted() {
        this.completed = true;
    }

    public void taskUncompleted() {
        this.completed = false;
    }
}
