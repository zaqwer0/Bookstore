package by.example.bookstore_api.config.exceptionHandler;

public class UserExists extends RuntimeException {
    public UserExists(String message) {
        super(message);
    }
}
