package by.example.bookstore_api.repository;

import by.example.bookstore_api.model.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
