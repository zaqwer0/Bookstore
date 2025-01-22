package by.example.bookstore_api.repository;

import by.example.bookstore_api.model.entity.Book;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends CrudRepository<Book, UUID> {

    @NonNull
    List<Book> findAll();


    Optional<Book> findByTitle(String title);

    boolean existsByTitleAndAuthor_LastName(String title, String authorLastName);
}
