package by.example.bookstore.api.model.dto.response;

import java.util.UUID;
import lombok.Builder;

@Builder
public record MyBookstoreResponseDto(UUID id, String bookTitle, int quantity) {}
