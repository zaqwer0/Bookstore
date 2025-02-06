package by.example.bookstore_api.strategy;

import by.example.bookstore_api.model.entity.Book;

import java.util.List;

public interface BookSortedStrategy {
  List<Book> sortBooks(List<Book> books);
}
