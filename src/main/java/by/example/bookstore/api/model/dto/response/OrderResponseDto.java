package by.example.bookstore.api.model.dto.response;

import java.util.Date;
import java.util.UUID;

public record OrderResponseDto(
    UUID id, UUID userId, UUID bookId, long quantity, Date orderDate, String status) {}
