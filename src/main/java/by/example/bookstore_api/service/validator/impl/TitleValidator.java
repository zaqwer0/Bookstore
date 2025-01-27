package by.example.bookstore_api.service.validator.impl;

import by.example.bookstore_api.model.dto.request.BookRequestDto;
import by.example.bookstore_api.model.entity.Book;
import by.example.bookstore_api.service.validator.interfaces.BookValidator;
import org.springframework.stereotype.Component;

@Component
public class TitleValidator implements BookValidator {

    private BookValidator bookValidator;

    @Override
    public boolean isValid(Book book) {
        return false;
    }

    @Override
    public void validate(BookRequestDto bookRequestDto) throws IllegalArgumentException {
        if (bookRequestDto.title() == null || bookRequestDto.title().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (bookValidator != null) {
            bookValidator.validate(bookRequestDto);
        }
    }

    @Override
    public void next(BookValidator next) {
    this.bookValidator = next;
    }
}
