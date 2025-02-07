package by.example.bookstore_api.kafka;

import by.example.bookstore_api.model.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
  private final KafkaTemplate<String, OrderEventDto> kafkaTemplate;

  private final KafkaTemplate<String, String> orderStatusKafkaTemplate;

  public void sendInventoryReq(Order order) {
    String bookTitle = order.getBook().getTitle();

    OrderEventDto orderEventDto =
        OrderEventDto.builder()
            .bookTitle(bookTitle)
                .quantity(order.getQuantity())
            .build();
    kafkaTemplate.send("bookstore", orderEventDto);
  }
}
