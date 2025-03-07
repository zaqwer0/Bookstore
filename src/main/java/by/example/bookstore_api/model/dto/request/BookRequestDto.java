package by.example.bookstore_api.model.dto.request;

import lombok.Builder;

@Builder
public record BookRequestDto(
        String title,
        String authorName,
        String authorLastName,
        double price,
        Integer publishedYear,
        String bookstoreName
) {
}
