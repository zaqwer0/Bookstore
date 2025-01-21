package by.example.bookstore_api.repository;

import by.example.bookstore_api.model.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
