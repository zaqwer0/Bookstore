package by.example.bookstore_api.model.dto.response;

import java.util.UUID;

public record AuthorResponseDto(
        UUID id,
        String name,
        String lastname
) {
}
