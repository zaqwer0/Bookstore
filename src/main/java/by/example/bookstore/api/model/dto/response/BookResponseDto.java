package by.example.bookstore.api.model.dto.response;

import lombok.Builder;

import java.io.Serializable;
import java.util.UUID;

@Builder
public record BookResponseDto (
    UUID id,
    String title,
    String authorName,
    String authorLastName,
    double price,
    Integer publishedYear) implements Serializable {}
