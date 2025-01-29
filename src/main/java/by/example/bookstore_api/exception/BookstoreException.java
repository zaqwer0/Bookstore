package by.example.bookstore_api.exception;

public class BookstoreException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public BookstoreException(String message) {
    super(message);
  }
}
