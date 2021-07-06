package io.github.bhhan.example.order.domain;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.common.domain.shop.OptionGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class OrderOptionGroup {
    private String name;
    private List<OrderOption> orderOptions = new ArrayList<>();

    @Builder
    public OrderOptionGroup(String name, List<OrderOption> orderOptions){
        this.name = name;
        this.orderOptions.addAll(orderOptions);
    }

    public Money calculatePrice(){
        return Money.sum(orderOptions, OrderOption::getPrice);
    }

    public OptionGroup convertToOptionGroup(){
        return OptionGroup.builder()
                .name(name)
                .options(orderOptions.stream()
                .map(OrderOption::convertToOption)
                .collect(Collectors.toList()))
                .build();
    }
}
