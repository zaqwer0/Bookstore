package by.example.bookstore_api.kafka;

import by.example.bookstore_api.model.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, OrderEventDto> kafkaTemplate;

    private String orderStatusTopic = "order-status";
    private final KafkaTemplate<String, String> orderStatusKafkaTemplate;

    public void sendInventoryReq(Order order) {
        kafkaTemplate.send("inventory-req", new OrderEventDto());
    }

    public void sendInventoryResponse(String message) {
        kafkaTemplate.send("books", new OrderEventDto());
    }
}

