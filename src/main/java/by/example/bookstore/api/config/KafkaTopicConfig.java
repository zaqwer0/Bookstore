package by.example.bookstore.api.config;

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
  @Bean
  public NewTopic topic2() {
    return TopicBuilder.name("topic-2").partitions(3).replicas(1).build();
  }
  @Bean
  NewTopic topic3() {
    return TopicBuilder.name("user-creation-event").partitions(2).replicas(1).build();
  }
  @Bean
  NewTopic topic4() {
    return TopicBuilder.name("fullOrder").partitions(2).replicas(1).build();
  }
}
