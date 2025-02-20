package by.example.bookstore_api.kafka.dto.events;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserCreationEventDto(UUID id, String email) {}