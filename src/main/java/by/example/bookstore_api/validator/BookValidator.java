package by.example.bookstore_api.validator;

import by.example.bookstore_api.model.dto.request.BookRequestDto;

public interface BookValidator {

  void validate(BookRequestDto bookRequestDto) throws IllegalArgumentException;
}
