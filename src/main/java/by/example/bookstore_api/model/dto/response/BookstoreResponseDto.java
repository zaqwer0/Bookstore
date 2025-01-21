package by.example.bookstore_api.model.dto.response;

import java.util.UUID;

public record BookstoreResponseDto(
        UUID id,
        String name
) {
}
