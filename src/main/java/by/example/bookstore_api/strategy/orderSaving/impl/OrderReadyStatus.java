package by.example.bookstore_api.strategy.orderSaving.impl;

import by.example.bookstore_api.mapper.OrderMapper;
import by.example.bookstore_api.model.dto.request.OrderRequestDto;
import by.example.bookstore_api.model.dto.response.OrderResponseDto;
import by.example.bookstore_api.model.entity.Book;
import by.example.bookstore_api.model.entity.MyBookstore;
import by.example.bookstore_api.model.entity.Order;
import by.example.bookstore_api.model.entity.User;
import by.example.bookstore_api.model.enumeration.OrderStatus;
import by.example.bookstore_api.repository.MyBookstoreRepository;
import by.example.bookstore_api.repository.OrderRepository;
import by.example.bookstore_api.strategy.orderSaving.OrderStatusStrategy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderReadyStatus implements OrderStatusStrategy {

  @Override
  public OrderResponseDto saveOrder(
      MyBookstore myBookstore,
      OrderRequestDto orderRequestDto,
      MyBookstoreRepository myBookstoreRepository,
      OrderMapper orderMapper,
      OrderRepository orderRepository,
      Book book,
      User user) {
    Order order =
        Order.builder()
            .user(user)
            .book(book)
            .quantity(orderRequestDto.quantity())
            .orderDate(LocalDateTime.now())
            .orderStatus(OrderStatus.READY)
            .build();

    Order savedOrder = orderRepository.save(order);
    myBookstore.setQuantity(myBookstore.getQuantity() - orderRequestDto.quantity());
    myBookstoreRepository.save(myBookstore);

    return orderMapper.toOrderResponseDto(savedOrder);
  }
}
