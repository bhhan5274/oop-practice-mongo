package io.github.bhhan.example.order.usecase;

import io.github.bhhan.example.order.domain.Order;
import io.github.bhhan.example.order.domain.OrderLineItem;
import io.github.bhhan.example.order.domain.OrderOption;
import io.github.bhhan.example.order.domain.OrderOptionGroup;
import io.github.bhhan.example.order.usecase.dto.Cart;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public Order mapFrom(Cart cart){
        return Order.builder()
                .userId(cart.getUserId())
                .shopId(cart.getShopId())
                .orderLineItems(cart.getCartLineItems()
                .stream()
                .map(this::toOrderLineItem)
                .collect(Collectors.toList()))
                .build();
    }

    private OrderLineItem toOrderLineItem(Cart.CartLineItem cartLineItem){
        return OrderLineItem.builder()
                .count(cartLineItem.getCount())
                .name(cartLineItem.getName())
                .menuId(cartLineItem.getMenuId())
                .groups(cartLineItem.getGroups()
                .stream()
                .map(this::toOrderOptionGroup)
                .collect(Collectors.toList()))
                .build();
    }

    private OrderOptionGroup toOrderOptionGroup(Cart.CartOptionGroup cartOptionGroup){
        return OrderOptionGroup.builder()
                .name(cartOptionGroup.getName())
                .orderOptions(cartOptionGroup.getOptions()
                    .stream()
                    .map(this::toOrderOption)
                    .collect(Collectors.toList()))
                .build();
    }

    private OrderOption toOrderOption(Cart.CartOption cartOption){
        return OrderOption.builder()
                .name(cartOption.getName())
                .price(cartOption.getPrice())
                .build();
    }
}
