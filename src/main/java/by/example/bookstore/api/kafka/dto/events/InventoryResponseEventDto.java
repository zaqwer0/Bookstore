package by.example.bookstore.api.kafka.dto.events;

import lombok.Builder;

@Builder
public record InventoryResponseEventDto(String bookTitle, Long quantity, boolean available ) {}
