package by.example.bookstore.api.exception;

public class UserExists extends RuntimeException {
  public UserExists(String message) {
    super(message);
  }
}
