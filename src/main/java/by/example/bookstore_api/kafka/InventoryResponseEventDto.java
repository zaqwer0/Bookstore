package by.example.bookstore_api.kafka;

import lombok.Builder;

@Builder
public record InventoryResponseEventDto(String bookTitle, Long quantity, boolean available ) {}
