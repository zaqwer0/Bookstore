package by.example.bookstore_api.model.dto.response;

import java.util.UUID;

public record BookResponseDto(
        UUID id,
        String title,
        String authorName,
        String authorLastName,
        double price,
        Integer publishedYear,
        String bookstoreName
) {
}
