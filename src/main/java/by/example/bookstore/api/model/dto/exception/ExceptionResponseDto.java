package by.example.bookstore.api.model.dto.exception;

import lombok.Builder;

@Builder
public record ExceptionResponseDto(String message, int status, String error, String path) {}
