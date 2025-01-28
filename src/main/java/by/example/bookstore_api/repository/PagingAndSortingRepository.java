package by.example.bookstore_api.repository;

import by.example.bookstore_api.model.entity.Book;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;

@Repository
//todo YAGNI
public interface PagingAndSortingRepository {

    Optional<Book> findByTitle(String title);

    boolean existsByTitleAndAuthorLastname(String title, String authorLastName);

    Object findAll(Pageable pageable);
}
