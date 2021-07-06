package io.github.bhhan.example.billing.domain;

import io.github.bhhan.example.common.domain.money.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "billings")
@Getter
@NoArgsConstructor
public class Billing {
    @Id
    private String id;

    private String shopId;
    private Money commission = Money.ZERO;

    public Billing(String shopId){
        this(null, shopId, Money.ZERO);
    }

    @Builder
    public Billing(String id, String shopId, Money commission){
        this.id = id;
        this.shopId = shopId;
        this.commission = commission;
    }

    public void billCommissionFee(Money commission){
        this.commission = this.commission.plus(commission);
    }
}
