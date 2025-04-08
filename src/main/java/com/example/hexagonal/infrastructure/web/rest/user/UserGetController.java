package com.example.hexagonal.infrastructure.web.rest.user;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.hexagonal.application.usecase.user.find.FindUserUseCase;
import com.example.hexagonal.domain.User;
import com.example.hexagonal.domain.UserId;
import com.example.hexagonal.infrastructure.mapper.UserMapper;
import com.example.hexagonal.infrastructure.web.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserGetController {

    private final FindUserUseCase findUserUseCase;

    @GetMapping
    public List<UserResponse> getUserByIds(@RequestBody List<UserId> userIds) {
        List<User> users = findUserUseCase.getByIds(userIds);
        return users.stream().map(UserMapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable UserId userId) {
        User user = findUserUseCase.getById(userId);
        return UserMapper.toResponse(user);
    }
}
