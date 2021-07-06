package io.github.bhhan.example.shop.usecase.dto;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.shop.domain.Menu;
import io.github.bhhan.example.shop.domain.OptionGroupSpecification;
import io.github.bhhan.example.shop.domain.OptionSpecification;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class MenuDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class MenuReq {
        private String name;
        private String description;
        private String shopId;
        private OptionGroupSpecificationReq basic;
        private List<OptionGroupSpecificationReq> additives;

        @Builder
        public MenuReq(String name, String description, String shopId, OptionGroupSpecificationReq basic, List<OptionGroupSpecificationReq> additives) {
            this.name = name;
            this.description = description;
            this.shopId = shopId;
            this.basic = basic;
            this.additives = additives;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class OptionGroupSpecificationReq {
        private String name;
        private boolean exclusive;
        private boolean basic;
        private List<OptionSpecificationReq> optionSpecs;

        @Builder
        public OptionGroupSpecificationReq(String name, boolean exclusive, boolean basic, List<OptionSpecificationReq> optionSpecs) {
            this.name = name;
            this.exclusive = exclusive;
            this.basic = basic;
            this.optionSpecs = optionSpecs;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class OptionSpecificationReq {
        private String name;
        private Money price;

        @Builder
        public OptionSpecificationReq(String name, Money price) {
            this.name = name;
            this.price = price;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class MenuRes {
        private String id;
        private String name;
        private String description;
        private String shopId;
        private List<OptionGroupSpecificationRes> optionGroupSpecs;

        @Builder
        public MenuRes(Menu menu) {
            this.id = menu.getId();
            this.name = menu.getName();
            this.description = menu.getDescription();
            this.shopId = menu.getShopId();
            this.optionGroupSpecs = menu.getOptionGroupSpecs()
                    .stream()
                    .map(OptionGroupSpecificationRes::new)
                    .collect(Collectors.toList());
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class OptionGroupSpecificationRes {
        private String id;
        private String name;
        private boolean exclusive;
        private boolean basic;
        private List<OptionSpecificationRes> optionSpecs;

        @Builder
        public OptionGroupSpecificationRes(OptionGroupSpecification optionGroupSpecification) {
            this.id = optionGroupSpecification.getId();
            this.name = optionGroupSpecification.getName();
            this.exclusive = optionGroupSpecification.isExclusive();
            this.basic = optionGroupSpecification.isBasic();
            this.optionSpecs = optionGroupSpecification.getOptionSpecs()
                    .stream()
                    .map(OptionSpecificationRes::new)
                    .collect(Collectors.toList());
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class OptionSpecificationRes {
        private String id;
        private String name;
        private Money price;

        @Builder
        public OptionSpecificationRes(OptionSpecification optionSpecification) {
            this.id = optionSpecification.getId();
            this.name = optionSpecification.getName();
            this.price = optionSpecification.getPrice();
        }
    }
}
