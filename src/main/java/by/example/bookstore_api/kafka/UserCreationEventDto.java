package by.example.bookstore_api.kafka;

import lombok.Builder;

import java.util.UUID;
@Builder
public record UserCreationEventDto(UUID id, String email) {}
