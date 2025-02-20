package by.example.bookstore_api.kafka;

import by.example.bookstore_api.kafka.dto.events.UserCreationEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerUserService {
    private final KafkaTemplate<String, UserCreationEventDto> kafkaTemplate;

    public void sendUserCreationEvent(UserCreationEventDto userCreationEventDto) {

        kafkaTemplate.send("user-creation-event", userCreationEventDto);
    }
}
