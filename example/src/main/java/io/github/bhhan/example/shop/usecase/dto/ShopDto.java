package io.github.bhhan.example.shop.usecase.dto;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.common.domain.money.Ratio;
import io.github.bhhan.example.shop.domain.Shop;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ShopDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class ShopReq {
        private String name;
        private boolean open;
        private Money minOrderAmount;
        private Ratio commissionRate;

        @Builder
        public ShopReq(String name, boolean open, Money minOrderAmount, Ratio commissionRate){
            this.name = name;
            this.open = open;
            this.minOrderAmount = minOrderAmount;
            this.commissionRate = commissionRate;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ShopRes {
        private String id;
        private String name;
        private boolean open;
        private Money minOrderAmount;
        private Ratio commissionRate;

        @Builder
        public ShopRes(Shop shop){
            this.id = shop.getId();
            this.name = shop.getName();
            this.open = shop.isOpen();
            this.minOrderAmount = shop.getMinOrderAmount();
            this.commissionRate = shop.getCommissionRate();
        }
    }
}
