package io.github.bhhan.example.order.usecase.dto;


import io.github.bhhan.example.common.domain.money.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Cart {
    private String shopId;
    private String userId;
    private List<CartLineItem> cartLineItems = new ArrayList<>();

    @Builder
    public Cart(String shopId, String userId, CartLineItem... cartLineItems){
        this.shopId = shopId;
        this.userId = userId;
        this.cartLineItems = Arrays.asList(cartLineItems);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CartLineItem {
        private String menuId;
        private String name;
        private int count;
        private List<CartOptionGroup> groups = new ArrayList<>();

        @Builder
        public CartLineItem(String menuId, String name, int count, CartOptionGroup... groups){
            this.menuId = menuId;
            this.name = name;
            this.count = count;
            this.groups = Arrays.asList(groups);
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CartOptionGroup{
        private String name;
        private List<CartOption> options = new ArrayList<>();

        @Builder
        public CartOptionGroup(String name, CartOption... options){
            this.name = name;
            this.options = Arrays.asList(options);
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CartOption{
        private String name;
        private Money price;

        @Builder
        public CartOption(String name, Money price){
            this.name = name;
            this.price = price;
        }
    }
}
