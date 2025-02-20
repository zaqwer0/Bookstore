package by.example.bookstore_api.kafka;

import by.example.bookstore_api.kafka.dto.events.OrderEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerOrderService {
  private final KafkaTemplate<String, OrderEventDto> kafkaTemplate;

  public void sendInventoryReq(OrderEventDto order) {

    kafkaTemplate.send("topic-2", order);
  }
}
