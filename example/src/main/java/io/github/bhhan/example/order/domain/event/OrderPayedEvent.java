package io.github.bhhan.example.order.domain.event;

import io.github.bhhan.example.common.event.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderPayedEvent implements DomainEvent {
    private String orderId;

    public OrderPayedEvent(String orderId) {
        this.orderId = orderId;
    }
}
