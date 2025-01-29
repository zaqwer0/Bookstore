package by.example.bookstore_api.config.exceptionHandler;

// i can just use this class instead of exp classes (author,user) i'm just not sure what is the best solution
public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String message) {
        super(message);
    }
}
