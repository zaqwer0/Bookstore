package by.example.bookstore_api.kafka.dto.events;

import lombok.Builder;

@Builder
public record OrderEventDto(String bookTitle, int quantity) {}
