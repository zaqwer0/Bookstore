package by.example.bookstore.api.model.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record AuthorResponseDto(UUID id, String name, String lastname) {}
