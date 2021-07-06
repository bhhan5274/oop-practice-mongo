package io.github.bhhan.example.common.domain.shop;

import io.github.bhhan.example.common.domain.money.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by hbh5274@gmail.com on 2021-04-22
 * Github : http://github.com/bhhan5274
 */

@Getter
@Setter
@NoArgsConstructor
public class Option {
    private String name;
    private Money price;

    @Builder
    public Option(String name, Money price){
        this.name = name;
        this.price = price;
    }
}
