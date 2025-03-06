package by.example.bookstore.api.validator.impl;

import by.example.bookstore.api.model.dto.request.BookRequestDto;
import by.example.bookstore.api.validator.BookValidator;
import org.springframework.stereotype.Component;

@Component
public class PriceValidator implements BookValidator {

  @Override
  public void validate(BookRequestDto bookRequestDto) {
    if (bookRequestDto.price() <= 0) {
      throw new IllegalArgumentException("Price must be more than 0");
    }
  }
}
