package by.example.bookstore_api.repository;

import by.example.bookstore_api.model.entity.Bookstore;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookstoreRepository extends CrudRepository<Bookstore, UUID> {
    @NonNull
    @Override
    List<Bookstore> findAll();

    Optional<Bookstore> findByName(String name);

    List<Bookstore> findByNameContainingIgnoreCase(String username);

    boolean existsByName(String name);
}
