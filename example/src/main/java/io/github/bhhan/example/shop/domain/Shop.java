package io.github.bhhan.example.shop.domain;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.common.domain.money.Ratio;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shops")
@Getter
@NoArgsConstructor
public class Shop {
    @Id
    private String id;

    private String name;
    private boolean open;

    private Money minOrderAmount;
    private Ratio commissionRate;

    @Builder
    public Shop(String id, String name, boolean open, Money minOrderAmount, Ratio commissionRate){
        this.id = id;
        this.name = name;
        this.open = open;
        this.minOrderAmount = minOrderAmount;
        this.commissionRate = commissionRate;
    }

    public boolean isValidOrderAmount(Money amount){
        return amount.isGreaterThanEqual(minOrderAmount);
    }

    public void open(){
        this.open = true;
    }

    public void close(){
        this.open = false;
    }

    public void modifyCommissionRate(Ratio commissionRate){
        this.commissionRate = commissionRate;
    }

    public Money calculateCommissionFee(Money price){
        return commissionRate.of(price);
    }
}
