package com.example.hexagonal.infrastructure.mapper;

import com.example.hexagonal.domain.User;
import com.example.hexagonal.domain.UserId;
import com.example.hexagonal.infrastructure.db.entity.UserEntity;
import com.example.hexagonal.infrastructure.db.entity.UserRole;

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
}
