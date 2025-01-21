package by.example.bookstore_api.repository;

import by.example.bookstore_api.model.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookstoreRepository extends CrudRepository<Book, Long> {
}
