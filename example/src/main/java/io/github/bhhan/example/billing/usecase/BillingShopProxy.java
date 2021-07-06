package io.github.bhhan.example.billing.usecase;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.shop.web.ShopController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BillingShopProxy {
    private final ShopController shopController;

    public Money calculateCommissionFee(String shopId, Money totalPrice){
        return shopController.calculateCommissionFee(shopId, totalPrice);
    }
}
