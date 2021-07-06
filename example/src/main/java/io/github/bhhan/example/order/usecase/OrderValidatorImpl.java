package io.github.bhhan.example.order.usecase;

import io.github.bhhan.example.order.domain.Order;
import io.github.bhhan.example.order.domain.OrderLineItem;
import io.github.bhhan.example.order.domain.OrderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderValidatorImpl implements OrderValidator {
    private final MenuProxy menuProxy;
    private final ShopProxy shopProxy;

    @Override
    public void validate(Order order) {
        if(!shopProxy.isOpen(order.getShopId())){
            throw new IllegalArgumentException("가게가 영업중이 아닙니다.");
        }

        if(order.getOrderLineItems().isEmpty()){
            throw new IllegalArgumentException("주문 항목이 비어 있습니다.");
        }

        if(!shopProxy.isValidOrderAmount(order.getShopId(), order.calculateTotalPrice())){
            throw new IllegalStateException("최소 주문 금액 이상을 주문해주세요.");
        }

        for (OrderLineItem orderLineItem : order.getOrderLineItems()) {
            if(!menuProxy.validateOrder(orderLineItem.getMenuId(), orderLineItem.getName(), orderLineItem.convertToOptionGroups())){
                throw new IllegalArgumentException("메뉴가 변경됐습니다.");
            }
        }
    }
}
