package by.example.bookstore_api.kafka;

import jakarta.persistence.Column;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "topic-inventory", groupId = "intventory")
    void listener(String data) {
        log.info("Received: {}", data);
    }
}
