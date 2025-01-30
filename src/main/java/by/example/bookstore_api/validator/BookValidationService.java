package by.example.bookstore_api.validator;

import by.example.bookstore_api.model.dto.request.BookRequestDto;
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
