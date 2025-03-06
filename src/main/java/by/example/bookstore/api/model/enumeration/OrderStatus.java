package by.example.bookstore.api.model.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
  PROCESSING("The order is being processed"),
  READY("The order is ready for confirmation"),
  DONE("The order is completed"),
  REFUSAL("The order is canceled");

  private final String description;
}
