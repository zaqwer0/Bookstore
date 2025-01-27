package by.example.bookstore_api.service.validator.interfaces;

import by.example.bookstore_api.model.dto.request.BookRequestDto;
import by.example.bookstore_api.model.entity.Book;

public interface BookValidator {
    boolean isValid(Book book);
    void validate(BookRequestDto bookRequestDto) throws IllegalArgumentException;
    void next(BookValidator next);
}
