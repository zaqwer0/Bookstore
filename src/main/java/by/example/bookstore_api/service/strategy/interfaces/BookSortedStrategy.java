package by.example.bookstore_api.service.strategy.interfaces;

import by.example.bookstore_api.model.entity.Book;

import java.util.List;

//todo WTF is interfaces package
public interface BookSortedStrategy {
    List<Book> sortBooks(List<Book> books);
}
