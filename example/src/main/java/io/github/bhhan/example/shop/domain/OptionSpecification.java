package io.github.bhhan.example.shop.domain;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.common.domain.shop.Option;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "option_specs")
@Getter
@NoArgsConstructor
public class OptionSpecification {
    @Id
    private String id;

    private String name;
    private Money price;

    @Builder
    public OptionSpecification(String id, String name, Money price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionSpecification that = (OptionSpecification) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean isSatisfiedBy(Option option){
        return Objects.equals(name, option.getName()) && Objects.equals(price, option.getPrice());
    }
}
