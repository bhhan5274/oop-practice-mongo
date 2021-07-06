package io.github.bhhan.example.order.domain;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.common.domain.shop.Option;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderOption {
    private String name;
    private Money price;

    @Builder
    public OrderOption(String name, Money price){
        this.name = name;
        this.price = price;
    }

    public Option convertToOption(){
        return Option.builder()
                .name(name)
                .price(price)
                .build();
    }
}
