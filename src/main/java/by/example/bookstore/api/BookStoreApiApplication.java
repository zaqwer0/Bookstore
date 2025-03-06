package by.example.bookstore.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BookStoreApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(BookStoreApiApplication.class, args);
  }
}
