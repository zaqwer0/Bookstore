package by.example.bookstore_api.repository;

import by.example.bookstore_api.model.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    @NonNull
    List<User> findAll();

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);
}
