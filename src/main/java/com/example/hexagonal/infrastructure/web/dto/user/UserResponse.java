package com.example.hexagonal.infrastructure.web.dto.user;

import java.util.UUID;

public record UserResponse(UUID id, String name, String email, String role) {

}
