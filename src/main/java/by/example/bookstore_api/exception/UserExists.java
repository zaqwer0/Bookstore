package by.example.bookstore_api.exception;

public class UserExists extends RuntimeException {
  public UserExists(String message) {
    super(message);
  }
}
