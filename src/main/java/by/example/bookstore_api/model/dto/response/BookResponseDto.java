package by.example.bookstore_api.model.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record BookResponseDto(
    UUID id,
    String title,
    String authorName,
    String authorLastName,
    double price,
    Integer publishedYear) {}
