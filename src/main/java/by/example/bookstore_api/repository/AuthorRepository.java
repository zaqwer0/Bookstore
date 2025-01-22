package by.example.bookstore_api.repository;

import by.example.bookstore_api.model.entity.Author;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends CrudRepository<Author, UUID> {
    @NonNull
    @Override
    List<Author> findAll();

    Optional<Author> findByLastName(String lastName);

    boolean existsByLastNameAndName(String lastName, String name);
}
