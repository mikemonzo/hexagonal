package com.example.hexagonal.infrastructure.web.rest.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.hexagonal.application.usecase.user.create.CreateUserUseCase;
import com.example.hexagonal.domain.User;
import com.example.hexagonal.infrastructure.mapper.UserMapper;
import com.example.hexagonal.infrastructure.web.dto.user.UserRequest;
import com.example.hexagonal.infrastructure.web.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserPostController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping("/auth/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        User user = createUserUseCase.create(UserMapper.toCommand(userRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));
    }
}
