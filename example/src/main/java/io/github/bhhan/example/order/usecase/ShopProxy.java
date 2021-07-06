package io.github.bhhan.example.order.usecase;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.shop.web.ShopController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShopProxy {
    private final ShopController shopController;

    public boolean isOpen(String shopId){
        return shopController.isOpen(shopId);
    }

    public boolean isValidOrderAmount(String shopId, Money amount){
        return shopController.isValidOrderAmount(shopId, amount);
    }
}
