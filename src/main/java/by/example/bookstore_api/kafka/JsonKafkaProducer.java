package by.example.bookstore_api.kafka;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JsonKafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(JsonKafkaProducer.class);

    private final KafkaTemplate<String, OrderEventDto> kafkaTemplate;

    public void send(OrderEventDto orderEventDto) {
        logger.info("Sending order event: {}", orderEventDto);

        Message<OrderEventDto> message = MessageBuilder
                .withPayload(orderEventDto)
                .setHeader(KafkaHeaders.TOPIC, "topic-2")
                .build();

        kafkaTemplate.send(message);
    }

}
