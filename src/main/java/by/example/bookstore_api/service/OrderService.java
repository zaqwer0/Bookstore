package by.example.bookstore_api.service;

import by.example.bookstore_api.model.dto.request.OrderRequestDto;
import by.example.bookstore_api.model.dto.response.OrderResponseDto;
import by.example.bookstore_api.strategy.orderSaving.OrderStatusStrategy;

import java.util.List;
import java.util.UUID;

public interface OrderService {

  OrderResponseDto findById(UUID orderId);

  List<OrderResponseDto> findAll();

  OrderResponseDto save(OrderRequestDto orderRequestDto);

  void delete(UUID orderId);

  void update(UUID orderId, OrderRequestDto orderRequestDto);
}
