package com.example.hexagonal.application.usecase.task.create;

import com.example.hexagonal.domain.UserId;
import com.example.hexagonal.domain.validation.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

/*
 * public record CreateTaskCommand(String name, String description, UserId author) { }
 */
@Getter
@Accessors(fluent = true)
public class CreateTaskCommand extends SelfValidating<CreateTaskCommand> {

    @NotBlank(message = "title cannot be blank")
    private final String title;
    @NotBlank(message = "description cannot be blank")
    private final String description;

    private final UserId author;

    @Builder
    public CreateTaskCommand(String title, String description, UserId author) {
        super(CreateTaskCommand.class);
        this.title = title;
        this.description = description;
        this.author = author;
        this.validateSelf();
    }
}
