package com.example.hexagonal.infrastructure.web.dto.task;

import java.time.LocalDateTime;

public record TaskResponse(Long id, String name, String description, LocalDateTime createAt,
        Boolean completed) {

}
