package by.example.bookstore.api.service.impl;

import by.example.bookstore.api.mapper.OrderMapper;
import by.example.bookstore.api.model.dto.request.OrderRequestDto;
import by.example.bookstore.api.model.dto.response.OrderResponseDto;
import by.example.bookstore.api.model.entity.Book;
import by.example.bookstore.api.model.entity.MyBookstore;
import by.example.bookstore.api.model.entity.Order;
import by.example.bookstore.api.model.entity.User;
import by.example.bookstore.api.repository.BookRepository;
import by.example.bookstore.api.repository.MyBookstoreRepository;
import by.example.bookstore.api.repository.OrderRepository;
import by.example.bookstore.api.repository.UserRepository;
import by.example.bookstore.api.service.OrderService;
import by.example.bookstore.api.strategy.orderSaving.OrderStatusStrategy;
import by.example.bookstore.api.strategy.orderSaving.impl.OrderProcessingStatus;
import by.example.bookstore.api.strategy.orderSaving.impl.OrderReadyStatus;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final MyBookstoreRepository myBookstoreRepository;
  private final OrderReadyStatus orderReadyStatus;
  private final OrderProcessingStatus orderProcessingStatus;

    public OrderResponseDto findById(UUID orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::toOrderResponseDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id %s not found", orderId)));
    }

    public List<OrderResponseDto> findAll() {
        return orderMapper.toOrderResponse(orderRepository.findAll());
    }

  // todo divide current logic (done)
  public OrderResponseDto save(OrderRequestDto orderRequestDto) {
    User user =
        userRepository
            .findById(orderRequestDto.userId())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        String.format("User with id=%s not found", orderRequestDto.userId())));

    Book book =
        bookRepository
            .findById(orderRequestDto.bookId())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        String.format("Book with id=%s not found", orderRequestDto.bookId())));

    MyBookstore myBookstore =
        myBookstoreRepository
            .findByBookId(book.getId())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        String.format("MyBookstore with id=%s not found", book.getId())));

    boolean exist = myBookstore.getQuantity() >= orderRequestDto.quantity();

    OrderStatusStrategy orderStatusStrategy;
    orderStatusStrategy = exist ? orderReadyStatus : orderProcessingStatus;

    return orderStatusStrategy.saveOrder(
        myBookstore,
        orderRequestDto,
        myBookstoreRepository,
        orderMapper,
        orderRepository,
        book,
        user);
    }

    public void delete(UUID orderId) {
        orderRepository.deleteById(orderId);
    }


    public void update(UUID orderId, OrderRequestDto orderRequestDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id=%s not found", orderId)));

        order.setQuantity(orderRequestDto.quantity());

        orderRepository.save(order);
    }


}