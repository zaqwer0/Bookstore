package by.example.bookstore_api.model.dto.response;

import lombok.Builder;

import java.util.UUID;
@Builder
public record BookstoreResponseDto(
        UUID id,
        String name
) {
}
