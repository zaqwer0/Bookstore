package by.example.bookstore_api.kafka;

import lombok.Builder;

@Builder
public record OrderEventDto(String bookTitle, int quantity) {
}
