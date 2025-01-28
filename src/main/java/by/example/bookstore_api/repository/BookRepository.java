package by.example.bookstore_api.repository;

import by.example.bookstore_api.model.entity.Book;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    @NonNull
    List<Book> findAll();


    Optional<Book> findByTitle(String title);

    boolean existsByTitleAndAuthorLastname(String title, String authorLastName);
}
