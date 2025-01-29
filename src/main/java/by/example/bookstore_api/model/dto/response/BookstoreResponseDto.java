package by.example.bookstore_api.model.dto.response;

import java.util.UUID;
import lombok.Builder;

@Builder
public record BookstoreResponseDto(
        UUID id,
        String name
) {
}
