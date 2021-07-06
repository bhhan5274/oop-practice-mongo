package io.github.bhhan.example.shop.usecase;

import io.github.bhhan.example.shop.domain.Shop;
import io.github.bhhan.example.shop.usecase.dto.ShopDto;
import org.springframework.stereotype.Component;

@Component
public class ShopMapper {
    public Shop fromShopReq(ShopDto.ShopReq shopReq){
        return new Shop(null, shopReq.getName(), shopReq.isOpen(), shopReq.getMinOrderAmount(), shopReq.getCommissionRate());
    }

    public ShopDto.ShopRes fromShop(Shop shop){
        return ShopDto.ShopRes.builder()
                .shop(shop)
                .build();
    }
}
