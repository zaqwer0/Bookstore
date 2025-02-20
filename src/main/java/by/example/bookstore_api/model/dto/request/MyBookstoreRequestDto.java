package by.example.bookstore_api.model.dto.request;

import jakarta.validation.constraints.Min;
import java.util.UUID;

public record MyBookstoreRequestDto(
    UUID bookId, @Min(value = 0, message = "Quantity must be greater than zero") int quantity) {}
