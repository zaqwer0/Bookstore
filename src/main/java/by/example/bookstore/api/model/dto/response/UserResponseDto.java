package by.example.bookstore.api.model.dto.response;

import java.util.Date;
import java.util.UUID;

public record UserResponseDto(
    UUID id, String username, String password, String email, Date registrationDate) {}
