package com.example.hexagonal.application.usecase.user.find;

import java.util.List;
import com.example.hexagonal.domain.User;
import com.example.hexagonal.domain.UserId;
import com.example.hexagonal.domain.error.UserNotFoundException;
import com.example.hexagonal.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindUserUseCase {

    private final UserRepository userRepository;

    public User getById(UserId userId) {
        return userRepository.getById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public List<User> getByIds(Iterable<UserId> userIds) {
        List<User> result = userRepository.getByIds(userIds);
        if (result.isEmpty()) {
            throw new UserNotFoundException();
        }
        return result;
    }
}
