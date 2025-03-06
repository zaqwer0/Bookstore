package by.example.bookstore.api.validator;

import by.example.bookstore.api.model.dto.request.BookRequestDto;

public interface BookValidator {

  void validate(BookRequestDto bookRequestDto) throws IllegalArgumentException;
}
