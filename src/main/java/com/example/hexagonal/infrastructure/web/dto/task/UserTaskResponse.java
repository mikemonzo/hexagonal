package com.example.hexagonal.infrastructure.web.dto.task;

import java.time.LocalDateTime;
import com.example.hexagonal.infrastructure.web.dto.user.UserResponse;

public record UserTaskResponse(Long id, String title, String description, LocalDateTime createdAt,
        boolean completed, UserResponse author) {

}
