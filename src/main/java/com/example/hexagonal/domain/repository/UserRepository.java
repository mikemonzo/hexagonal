package com.example.hexagonal.domain.repository;

import java.util.List;
import java.util.Optional;
import com.example.hexagonal.domain.User;
import com.example.hexagonal.domain.UserId;

public interface UserRepository {

    User create(User user);

    User uppdate(User user);

    Optional<User> changePassword(UserId userId, String password);

    Optional<User> getById(UserId userId);

    Optional<User> getByEmail(String email);

    List<User> getByIds(Iterable<UserId> ids);
}
