package by.example.bookstore_api.repository;

import by.example.bookstore_api.model.entity.Order;
import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, UUID> {
  @NonNull
  List<Order> findAll();
}
