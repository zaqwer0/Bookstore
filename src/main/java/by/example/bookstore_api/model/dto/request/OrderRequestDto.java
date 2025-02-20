package by.example.bookstore_api.model.dto.request;

import java.util.UUID;

public record OrderRequestDto(UUID userId, UUID bookId, int quantity) {}
