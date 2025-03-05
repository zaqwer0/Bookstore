package by.example.bookstore.api.validator.impl;

import by.example.bookstore.api.model.dto.request.BookRequestDto;
import by.example.bookstore.api.validator.BookValidator;
import org.springframework.stereotype.Component;

@Component
public class TitleValidator implements BookValidator {

  @Override
  public void validate(BookRequestDto bookRequestDto) throws IllegalArgumentException {
    if (bookRequestDto.title() == null || bookRequestDto.title().isEmpty()) {
      throw new IllegalArgumentException("Title cannot be empty");
    }
  }
}
