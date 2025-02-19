package by.example.bookstore_api.repository;

import by.example.bookstore_api.model.entity.Author;
import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, UUID> {
    @NonNull
    @Override
    List<Author> findAll();

    List<Author> findByLastnameContaining(@NonNull String lastName);

    boolean existsByLastnameAndName(String lastName, String name);
}
