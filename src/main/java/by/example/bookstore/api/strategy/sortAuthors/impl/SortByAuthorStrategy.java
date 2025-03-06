package by.example.bookstore.api.strategy.sortAuthors.impl;

import by.example.bookstore.api.model.entity.Book;
import by.example.bookstore.api.strategy.sortAuthors.BookSortedStrategy;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component("sortByAuthor")
public class SortByAuthorStrategy implements BookSortedStrategy {
  @Override
  public List<Book> sortBooks(List<Book> books) {
    return books.stream().sorted(Comparator.comparing(book -> book.getAuthor().getName())).toList();
  }
}
