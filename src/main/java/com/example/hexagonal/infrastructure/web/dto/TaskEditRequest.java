package com.example.hexagonal.infrastructure.web.dto;

public record TaskEditRequest(String title, String description, boolean completed) {

}
