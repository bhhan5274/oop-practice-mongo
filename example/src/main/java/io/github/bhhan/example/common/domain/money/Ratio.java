package io.github.bhhan.example.common.domain.money;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

/**
 * Created by hbh5274@gmail.com on 2021-04-22
 * Github : http://github.com/bhhan5274
 */

@Getter
@Setter
@NoArgsConstructor
public class Ratio {
    @Field(targetType = FieldType.DOUBLE)
    private Double rate;

    public static Ratio valueOf(Double rate){
        return new Ratio(rate);
    }

    private Ratio(Double rate){
        this.rate = rate;
    }

    public Money of(Money price){
        return price.times(rate);
    }

    public Double getRate(){
        return this.rate;
    }
}
