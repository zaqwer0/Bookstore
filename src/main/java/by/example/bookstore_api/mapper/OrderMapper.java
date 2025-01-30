package by.example.bookstore_api.mapper;

import by.example.bookstore_api.model.dto.request.OrderRequestDto;
import by.example.bookstore_api.model.dto.response.OrderResponseDto;
import by.example.bookstore_api.model.entity.Order;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    Order toOrder(OrderRequestDto orderDto);

  Order toOrder(OrderResponseDto orderResponseDto);

  List<OrderResponseDto> toOrderResponse(List<Order> orderRequestDtos);

  OrderResponseDto toOrderResponseDto(Order order);
}
