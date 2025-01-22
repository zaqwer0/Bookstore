package by.example.bookstore_api.repository;

import by.example.bookstore_api.model.dto.response.UserResponseDto;
import by.example.bookstore_api.model.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    @NonNull
    List<User> findAll();

    boolean existsByUsername(String username, String email);

    Optional<User> findByUsername(String username);
}
