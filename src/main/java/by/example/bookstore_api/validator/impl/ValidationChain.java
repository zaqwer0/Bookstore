package by.example.bookstore_api.validator.impl;

import by.example.bookstore_api.validator.BookValidator;

import java.util.List;

public class ValidationChain {
    private ValidationChain() {
    }

    public static BookValidator createValidator() {
        var titleValidator = new TitleValidator();
        var priceValidator = new PriceValidator();

        List<BookValidator> validators = List.of(titleValidator, priceValidator);

        return bookRequestDto -> validators.forEach(validator -> validator.validate(bookRequestDto));
    }
}
