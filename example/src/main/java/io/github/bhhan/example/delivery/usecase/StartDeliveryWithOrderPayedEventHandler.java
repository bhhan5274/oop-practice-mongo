package io.github.bhhan.example.delivery.usecase;

import io.github.bhhan.example.delivery.domain.Delivery;
import io.github.bhhan.example.delivery.domain.DeliveryRepository;
import io.github.bhhan.example.order.domain.event.OrderDeliveredEvent;
import io.github.bhhan.example.order.domain.event.OrderPayedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartDeliveryWithOrderPayedEventHandler {
    private final DeliveryRepository deliveryRepository;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = OrderPayedEvent.class)
    public void handle(OrderPayedEvent event){
        Delivery delivery = Delivery.started(event.getOrderId());
        deliveryRepository.save(delivery);

        log.info(String.format("OrderPayedEvent[%s] = orderId[%s] >> StartDeliveryWithOrderPayedEventHandler", LocalDateTime.now(), event.getOrderId()));
    }
}
