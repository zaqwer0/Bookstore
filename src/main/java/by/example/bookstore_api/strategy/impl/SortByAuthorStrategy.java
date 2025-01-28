package by.example.bookstore_api.strategy.impl;

import by.example.bookstore_api.model.entity.Book;
import by.example.bookstore_api.strategy.BookSortedStrategy;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component("sortByAuthor")

public class SortByAuthorStrategy implements BookSortedStrategy {
    @Override
    public List<Book> sortBooks(List<Book> books) {
        return books.stream()
                .sorted(Comparator.comparing(book -> book.getAuthor().getName()))
                .toList();
    }
}
