package by.example.bookstore_api.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
//rename enum package
public enum OrderStatus {

    //YAGNI
    PACKING("The order is in packing stage"),
    TRANSIT("Order on the way"),
    DELIVERED("The order has been delivered");

    private final String description;
}
