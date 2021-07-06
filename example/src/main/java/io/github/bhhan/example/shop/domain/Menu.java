package io.github.bhhan.example.shop.domain;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.common.domain.shop.OptionGroup;
import io.github.bhhan.example.shop.usecase.dto.MenuDto;
import io.github.bhhan.mongo.event.Cascade;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Document(collection = "menus")
@Getter
@NoArgsConstructor
public class Menu {
    @Id
    private String id;

    private String name;
    private String description;

    private String shopId;

    @DBRef
    @Cascade
    private List<OptionGroupSpecification> optionGroupSpecs = new ArrayList<>();

    @Builder
    public Menu(String id, String name, String description, String shopId,
                OptionGroupSpecification basic,
                Collection<OptionGroupSpecification> optionGroupSpecs){
        Objects.requireNonNull(optionGroupSpecs, "OptionGroupSpecs is nonNull");

        this.id = id;
        this.name = name;
        this.description = description;
        this.shopId = shopId;
        this.optionGroupSpecs.add(basic);
        this.optionGroupSpecs.addAll(optionGroupSpecs);
    }

    public void validateOrder(String menuName, List<OptionGroup> optionGroups){
        if(!this.name.equals(menuName)){
            throw new IllegalArgumentException("기본 상품이 변경되었습니다.");
        }

        if(!isSatisfiedBy(optionGroups)){
            throw new IllegalArgumentException("메뉴가 변경되었습니다.");
        }
    }

    public Money getBasePrice(){
        return getBasicOptionGroupSpec()
                .getOptionSpecs()
                .get(0)
                .getPrice();
    }

    private OptionGroupSpecification getBasicOptionGroupSpec() {
        return optionGroupSpecs
                .stream()
                .filter(OptionGroupSpecification::isBasic)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("기본 상품을 찾을 수 없습니다."));
    }

    private boolean isSatisfiedBy(List<OptionGroup> cartOptionGroups){
        return cartOptionGroups
                .stream()
                .allMatch(this::isSatisfiedBy);
    }

    private boolean isSatisfiedBy(OptionGroup optionGroup){
        return optionGroupSpecs
                .stream()
                .anyMatch(spec -> spec.isSatisfiedBy(optionGroup));
    }
}
