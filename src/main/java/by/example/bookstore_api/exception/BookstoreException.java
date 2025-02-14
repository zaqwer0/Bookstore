package by.example.bookstore_api.exception;

import java.io.Serial;

public class BookstoreException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  public BookstoreException(String message) {
    super(message);
  }

  public BookstoreException() {
    super("Something went wrong");
  }
}
