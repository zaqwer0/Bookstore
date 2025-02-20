package by.example.bookstore_api.strategy.orderSaving;

import by.example.bookstore_api.mapper.OrderMapper;
import by.example.bookstore_api.model.dto.request.OrderRequestDto;
import by.example.bookstore_api.model.dto.response.OrderResponseDto;
import by.example.bookstore_api.model.entity.Book;
import by.example.bookstore_api.model.entity.MyBookstore;
import by.example.bookstore_api.model.entity.User;
import by.example.bookstore_api.repository.MyBookstoreRepository;
import by.example.bookstore_api.repository.OrderRepository;

public interface OrderStatusStrategy {

  OrderResponseDto saveOrder(
      MyBookstore myBookstore,
      OrderRequestDto orderRequestDto,
      MyBookstoreRepository myBookstoreRepository,
      OrderMapper orderMapper,
      OrderRepository orderRepository,
      Book book,
      User user);
}
