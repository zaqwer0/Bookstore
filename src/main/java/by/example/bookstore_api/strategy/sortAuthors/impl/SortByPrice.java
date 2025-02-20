package by.example.bookstore_api.strategy.sortAuthors.impl;

import by.example.bookstore_api.model.entity.Book;
import by.example.bookstore_api.strategy.sortAuthors.BookSortedStrategy;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component("sortByPrice")
public class SortByPrice implements BookSortedStrategy {
  @Override
  public List<Book> sortBooks(List<Book> books) {
    return books.stream().sorted(Comparator.comparing(Book::getPrice)).toList();
  }
}
