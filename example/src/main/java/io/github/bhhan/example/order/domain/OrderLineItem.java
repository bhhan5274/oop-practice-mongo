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
public class OrderLineItem {
    private String menuId;
    private String name;
    private int count;

    private List<OrderOptionGroup> groups = new ArrayList<>();

    @Builder
    public OrderLineItem(String menuId, String name, int count,
                         List<OrderOptionGroup> groups){
        this.menuId = menuId;
        this.name = name;
        this.count = count;
        this.groups.addAll(groups);
    }

    public Money calculatePrice(){
        return Money.sum(groups, OrderOptionGroup::calculatePrice)
                .times((double) count);
    }

    public List<OptionGroup> convertToOptionGroups(){
        return groups.stream()
                .map(OrderOptionGroup::convertToOptionGroup)
                .collect(Collectors.toList());
    }
}
