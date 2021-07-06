package io.github.bhhan.example.common.domain.money;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

/**
 * Created by hbh5274@gmail.com on 2021-04-22
 * Github : http://github.com/bhhan5274
 */

@Getter
@Setter
@NoArgsConstructor
public class Money {
    public static final Money ZERO = Money.wons(0L);

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal amount;

    public static <T> Money sum(Collection<T> bags, Function<T, Money> monetary){
        return bags.stream()
                .map(monetary)
                .reduce(Money.ZERO, Money::plus);
    }

    public static Money wons(Long amount){
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(Double amount){
        return new Money(BigDecimal.valueOf(amount));
    }

    private Money(BigDecimal amount){
        this.amount = amount;
    }

    public Money plus(Money other){
        return new Money(this.amount.add(other.amount));
    }

    public Money minus(Money other){
        return new Money(this.amount.subtract(other.amount));
    }

    public Money times(Double percent){
        return new Money(this.amount.multiply(BigDecimal.valueOf(percent)));
    }

    public Money divide(Double divisor){
        return new Money(this.amount.divide(BigDecimal.valueOf(divisor)));
    }

    public boolean isLessThan(Money other){
        return this.amount.compareTo(other.amount) < 0;
    }

    public boolean isGreaterThanEqual(Money other){
        return this.amount.compareTo(other.amount) >= 0;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Long longValue(){
        return this.amount.longValue();
    }

    public Double doubleValue(){
        return this.amount.doubleValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(this.amount.doubleValue(), money.amount.doubleValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return this.amount.toString() + "원";
    }
}
