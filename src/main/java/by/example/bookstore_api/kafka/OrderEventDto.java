package by.example.bookstore_api.kafka;

import by.example.bookstore_api.model.enumeration.OrderStatus;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderEventDto {
  public long quantity;
  private UUID orderId;
  private OrderStatus orderStatus;
  private UUID userId;
  private UUID bookId;
}
