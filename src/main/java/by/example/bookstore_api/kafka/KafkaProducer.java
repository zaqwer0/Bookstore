package by.example.bookstore_api.kafka;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
  private final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

  private final KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String message) {
    logger.info(String.format("Sending message: %s", message));
    kafkaTemplate.send("bookstore", message);
  }
}
