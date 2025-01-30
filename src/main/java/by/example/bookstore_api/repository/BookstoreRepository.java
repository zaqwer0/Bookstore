package by.example.bookstore_api.repository;

import by.example.bookstore_api.model.entity.Book;
import by.example.bookstore_api.model.entity.Bookstore;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookstoreRepository extends CrudRepository<Bookstore, UUID> {
    @NonNull
    @Override
    List<Bookstore> findAll();

    Optional<Bookstore> findByName(String name);

    boolean existsByName(String name);
}
