package by.example.bookstore.api.repository;

import by.example.bookstore.api.model.entity.MyBookstore;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBookstoreRepository extends JpaRepository<MyBookstore, UUID> {

  Optional<MyBookstore> findByBookId(UUID bookId);
}
