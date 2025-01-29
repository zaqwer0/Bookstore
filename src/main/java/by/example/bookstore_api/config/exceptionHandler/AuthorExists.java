package by.example.bookstore_api.config.exceptionHandler;

public class AuthorExists extends RuntimeException {
    public AuthorExists(String message) {
        super(message);
    }
}
