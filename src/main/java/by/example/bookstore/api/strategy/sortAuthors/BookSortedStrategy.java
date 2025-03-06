package by.example.bookstore.api.strategy.sortAuthors;

import by.example.bookstore.api.model.entity.Book;

import java.util.List;

public interface BookSortedStrategy {
  List<Book> sortBooks(List<Book> books);
}
