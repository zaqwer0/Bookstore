package by.example.bookstore_api.exception;

public class AuthorExists extends RuntimeException {
  public AuthorExists(String message) {
    super(message);
  }
}
