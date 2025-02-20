package by.example.bookstore_api.repository;

import by.example.bookstore_api.model.entity.Book;
import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

  @NonNull
  List<Book> findAll();

  Page<Book> findAllByTitleContaining(@NonNull String title, Pageable pageable);

  boolean existsByTitleAndAuthorLastname(String title, String authorLastName);
}
