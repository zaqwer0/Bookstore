package by.example.bookstore_api.controller;

import by.example.bookstore_api.model.dto.request.OrderRequestDto;
import by.example.bookstore_api.model.dto.response.OrderResponseDto;
import by.example.bookstore_api.service.OrderService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController {

  private final OrderService orderService;

  @GetMapping("{id}")
  public ResponseEntity<OrderResponseDto> findById(@PathVariable("id") UUID orderId) {
    return ResponseEntity.ok(orderService.findById(orderId));
  }

  @GetMapping
  public ResponseEntity<List<OrderResponseDto>> findAll() {
    return ResponseEntity.ok(orderService.findAll());
  }

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody OrderRequestDto orderRequestDto) {
    orderService.save(orderRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("{id}")
  public ResponseEntity<Void> update(
      @PathVariable("id") UUID orderId, @RequestBody OrderRequestDto orderRequestDto) {
    orderService.update(orderId, orderRequestDto);
    return ResponseEntity.ok().build();
  }

  @RequestMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") UUID orderId) {
    orderService.delete(orderId);
    return ResponseEntity.noContent().build();
  }
}
