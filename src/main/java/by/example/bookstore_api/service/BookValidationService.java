package by.example.bookstore_api.service;

import by.example.bookstore_api.model.dto.request.BookRequestDto;
import validator.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookValidationService {

    private final List<BookValidator> bookValidators;

    public void validate(BookRequestDto bookRequestDto) {
        for (BookValidator validator : bookValidators) {
            validator.validate(bookRequestDto);
        }
    }
}
