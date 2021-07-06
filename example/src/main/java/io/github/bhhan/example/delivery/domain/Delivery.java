package io.github.bhhan.example.delivery.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "deliveries")
@Getter
@NoArgsConstructor
public class Delivery {
    public enum DeliveryStatus {DELIVERING, DELIVERED}

    @Id
    private String id;

    private String orderId;
    private DeliveryStatus deliveryStatus;

    public static Delivery started(String orderId){
        return new Delivery(orderId, DeliveryStatus.DELIVERING);
    }

    private Delivery(String orderId, DeliveryStatus deliveryStatus){
        this(null, orderId, deliveryStatus);
    }

    @Builder
    public Delivery(String id, String orderId, DeliveryStatus deliveryStatus){
        this.id = id;
        this.orderId = orderId;
        this.deliveryStatus = deliveryStatus;
    }

    public void complete(){
        this.deliveryStatus = DeliveryStatus.DELIVERED;
    }
}
