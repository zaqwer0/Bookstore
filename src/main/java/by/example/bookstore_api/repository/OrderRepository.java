package by.example.bookstore_api.repository;

import by.example.bookstore_api.model.dto.request.OrderRequestDto;
import by.example.bookstore_api.model.entity.Order;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Order, UUID> {
    @NonNull
    List<Order> findAll();


}
