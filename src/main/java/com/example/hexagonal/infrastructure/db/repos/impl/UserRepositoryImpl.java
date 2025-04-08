package com.example.hexagonal.infrastructure.db.repos.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.hexagonal.domain.User;
import com.example.hexagonal.domain.UserId;
import com.example.hexagonal.domain.repository.UserRepository;
import com.example.hexagonal.infrastructure.db.entity.UserEntity;
import com.example.hexagonal.infrastructure.db.repos.jpa.UserEntityRepositoryJpa;
import com.example.hexagonal.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserEntityRepositoryJpa userEntityRepositoryJpa;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        UserEntity userEntity = UserMapper.toPersistence(user);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return UserMapper.toDomain(userEntityRepositoryJpa.save(userEntity));
    }

    @Override
    public User uppdate(User user) {
        UserEntity userEntity = UserMapper.toPersistence(user);
        return UserMapper.toDomain(userEntityRepositoryJpa.save(userEntity));
    }

    @Override
    public Optional<User> changePassword(UserId userId, String newPassword) {
        Optional<UserEntity> userEntity =
                userEntityRepositoryJpa.findById(userId.getValue()).map(u -> {
                    u.setPassword(passwordEncoder.encode(newPassword));
                    return userEntityRepositoryJpa.save(u);
                });
        return userEntity.map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> getById(UserId userId) {
        return userEntityRepositoryJpa.findById(userId.getValue()).map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userEntityRepositoryJpa.findByEmail(email).map(UserMapper::toDomain);
    }

    @Override
    public List<User> getByIds(Iterable<UserId> ids) {
        List<UUID> uuids = StreamSupport.stream(ids.spliterator(), false).map(UserId::getValue)
                .collect(Collectors.toList());
        return userEntityRepositoryJpa.findAllById(uuids).stream().map(UserMapper::toDomain)
                .collect(Collectors.toList());
    }

}
