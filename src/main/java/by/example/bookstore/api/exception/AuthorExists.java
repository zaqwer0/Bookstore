package by.example.bookstore.api.exception;

public class AuthorExists extends RuntimeException {
  public AuthorExists(String message) {
    super(message);
  }
}
