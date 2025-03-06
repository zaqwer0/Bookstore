package by.example.bookstore.api.strategy.orderSaving;

import by.example.bookstore.api.mapper.OrderMapper;
import by.example.bookstore.api.model.dto.request.OrderRequestDto;
import by.example.bookstore.api.model.dto.response.OrderResponseDto;
import by.example.bookstore.api.model.entity.Book;
import by.example.bookstore.api.model.entity.MyBookstore;
import by.example.bookstore.api.model.entity.User;
import by.example.bookstore.api.repository.MyBookstoreRepository;
import by.example.bookstore.api.repository.OrderRepository;

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
