package com.example.hexagonal.infrastructure.web.dto;

public record TaskEditRequest(String name, String description, boolean completed) {

}
