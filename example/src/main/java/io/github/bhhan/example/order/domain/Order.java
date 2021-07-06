package io.github.bhhan.example.order.domain;

import io.github.bhhan.example.common.domain.money.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "orders")
@Getter
@NoArgsConstructor
public class Order {
    public enum OrderStatus {
        ORDERED, PAYED, DELIVERED
    }

    @Id
    private String id;

    private String userId;
    private String shopId;

    private List<OrderLineItem> orderLineItems = new ArrayList<>();
    private LocalDateTime orderedTime;

    private OrderStatus orderStatus;

    @Builder
    public Order(String id, String userId, String shopId, List<OrderLineItem> orderLineItems,
                 LocalDateTime orderedTime, OrderStatus orderStatus){
        this.id = id;
        this.userId = userId;
        this.shopId = shopId;
        this.orderedTime = orderedTime;
        this.orderStatus = orderStatus;
        this.orderLineItems.addAll(orderLineItems);
    }

    public void place(OrderValidator orderValidator){
        orderValidator.validate(this);
        ordered();
        this.orderedTime = LocalDateTime.now();
    }

    public Money calculateTotalPrice(){
        return Money.sum(orderLineItems, OrderLineItem::calculatePrice);
    }

    public void payed(){
        this.orderStatus = OrderStatus.PAYED;
    }

    public void delivered(){
        this.orderStatus = OrderStatus.DELIVERED;
    }

    public void ordered(){
        this.orderStatus = OrderStatus.ORDERED;
    }
}
