package com.example.hexagonal.infrastructure.web.dto.task;

public record TaskEditRequest(String name, String description, boolean completed) {

}
