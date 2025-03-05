package by.example.bookstore.api.repository;

import by.example.bookstore.api.model.entity.Order;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import by.example.bookstore.api.model.enumeration.OrderStatus;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, UUID> {
  @NonNull
  List<Order> findAll();

  Optional<Order> findByBookTitleAndOrderStatus(String bookTitle, OrderStatus orderStatus);
}
