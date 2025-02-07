package by.example.bookstore_api.controller;

import by.example.bookstore_api.kafka.JsonKafkaProducer;
import by.example.bookstore_api.kafka.OrderEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka/true")
@RequiredArgsConstructor
public class JsonMessageController {

    private final JsonKafkaProducer jsonKafkaProducer;
    @RequestMapping("/pub")
    public ResponseEntity<String> sendMessage(@RequestBody OrderEventDto orderEventDto) {
        jsonKafkaProducer.send(orderEventDto);
        return ResponseEntity.ok("Order event sent successfully");
    }
    }

