package by.example.bookstore_api.service;

import by.example.bookstore_api.mapper.OrderMapper;
import by.example.bookstore_api.model.dto.request.OrderRequestDto;
import by.example.bookstore_api.model.dto.response.OrderResponseDto;
import by.example.bookstore_api.model.entity.Book;
import by.example.bookstore_api.model.entity.Order;
import by.example.bookstore_api.model.entity.User;
import by.example.bookstore_api.model.enums.OrderStatus;
import by.example.bookstore_api.repository.BookRepository;
import by.example.bookstore_api.repository.OrderRepository;
import by.example.bookstore_api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public OrderResponseDto findById(UUID orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::toOrderResponseDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id %s not found", orderId)));
    }

    public List<OrderResponseDto> findAll() {
        return orderMapper.toOrderResponse(orderRepository.findAll());
    }

    public OrderResponseDto save(OrderRequestDto orderRequestDto) {
        User user = userRepository.findById(orderRequestDto.userId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id=%s not found", orderRequestDto.userId())));

        Book book = bookRepository.findById(orderRequestDto.bookId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Book with id=%s not found", orderRequestDto.bookId())));

        Order order = Order.builder()
                .user(user)
                .book(book)
                .quantity(orderRequestDto.quantity())
                .orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.PACKING)
                .build();

        Order savedOrder = orderRepository.save(order);

        return orderMapper.toOrderResponseDto(savedOrder);
    }

    public void delete(UUID orderId) {
        orderRepository.deleteById(orderId);
    }

    //there could be impemented more complex update logic
    public void update(UUID orderId, OrderRequestDto orderRequestDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id=%s not found", orderId)));

        order.setQuantity(orderRequestDto.quantity());

        orderRepository.save(order);
    }
}