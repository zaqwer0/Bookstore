package by.example.bookstore.api.strategy.orderSaving.impl;

import by.example.bookstore.api.kafka.KafkaProducerOrderService;
import by.example.bookstore.api.kafka.dto.events.OrderEventDto;
import by.example.bookstore.api.mapper.OrderMapper;
import by.example.bookstore.api.model.dto.request.OrderRequestDto;
import by.example.bookstore.api.model.dto.response.OrderResponseDto;
import by.example.bookstore.api.model.entity.Book;
import by.example.bookstore.api.model.entity.MyBookstore;
import by.example.bookstore.api.model.entity.Order;
import by.example.bookstore.api.model.entity.User;
import by.example.bookstore.api.model.enumeration.OrderStatus;
import by.example.bookstore.api.repository.MyBookstoreRepository;
import by.example.bookstore.api.repository.OrderRepository;
import by.example.bookstore.api.strategy.orderSaving.OrderStatusStrategy;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProcessingStatus implements OrderStatusStrategy {

  private final KafkaProducerOrderService kafkaProducerOrderService;

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
            .orderStatus(OrderStatus.PROCESSING)
            .build();
    orderRepository.save(order);

    OrderEventDto orderEventDto =
        OrderEventDto.builder()
            .bookTitle(order.getBook().getTitle())
            .quantity(order.getQuantity())
            .build();
    kafkaProducerOrderService.sendInventoryReq(orderEventDto);
    return orderMapper.toOrderResponseDto(order);
  }
}
