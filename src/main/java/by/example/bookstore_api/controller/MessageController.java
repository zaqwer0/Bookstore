package by.example.bookstore_api.controller;

import by.example.bookstore_api.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//todo comments required here
@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class MessageController {

  private final KafkaProducer kafkaProducer;

  @GetMapping("publish")
  public ResponseEntity<String> sendMessage(@RequestParam String message) {
    kafkaProducer.sendMessage(message);
    return ResponseEntity.ok("Message sent successfully");
  }
}
