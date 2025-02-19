package by.example.bookstore_api.kafka;

import by.example.bookstore_api.model.enumeration.OrderStatus;
import by.example.bookstore_api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaInventoryOrderStatusConsumer {
  private final OrderRepository orderRepository;

  @KafkaListener(topics = "inventory", groupId = "inventory")
  void listener(InventoryResponseEventDto data) {
    log.info("Received: {}", data);

    if (data.available()) {
      orderRepository.findByBookTitleAndOrderStatus(data.bookTitle(), OrderStatus.PROCESSING)
              .ifPresent(order -> {
                order.setOrderStatus(OrderStatus.READY);
                orderRepository.save(order);
                log.info("Order status updated to READY for book: {}", data.bookTitle());
              });

    }

    else {
      log.info("Order status updated to NOT READY for book: {}", data.bookTitle());
    }

  }
}
