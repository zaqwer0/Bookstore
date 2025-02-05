package by.example.bookstore_api.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class KafkaTopicConfig {


    @Bean
    public NewTopic topic1() {
        return new NewTopic("bookstore", 3, (short) 1);
    }

    public NewTopic topic2() {
        return TopicBuilder.name("topic-2").partitions(3).replicas(1).build();
    }
}