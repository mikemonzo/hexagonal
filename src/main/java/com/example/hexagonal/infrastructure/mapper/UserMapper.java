package com.example.hexagonal.infrastructure.mapper;

import com.example.hexagonal.application.usecase.user.create.CreateUserCommand;
import com.example.hexagonal.domain.User;
import com.example.hexagonal.domain.UserId;
import com.example.hexagonal.infrastructure.db.entity.UserEntity;
import com.example.hexagonal.infrastructure.db.entity.UserRole;
import com.example.hexagonal.infrastructure.web.dto.user.UserRequest;
import com.example.hexagonal.infrastructure.web.dto.user.UserResponse;

public class UserMapper {

    public static UserEntity toPersistence(User user) {
        return UserEntity.builder().id(user.getId() != null ? user.getId().getValue() : null)
                .name(user.getName()).email(user.getEmail()).password(user.getPassword())
                .role(UserRole.of(user.getRole())).build();
    }

    public static User toDomain(UserEntity userEntity) {
        return User.builder().id(UserId.of(userEntity.getId())).name(userEntity.getName())
                .email(userEntity.getEmail()).password(userEntity.getPassword())
                .role(userEntity.getRole().toString()).build();
    }

    public static UserResponse toDto(User user) {
        return new UserResponse(user.getId().getValue(), user.getName(), user.getEmail(),
                user.getRole());
    }

    public static CreateUserCommand toCommand(UserRequest userRequest) {
        return new CreateUserCommand(userRequest.email(), userRequest.name(),
                userRequest.password());
    }
}
