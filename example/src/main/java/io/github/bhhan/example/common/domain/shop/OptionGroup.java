package io.github.bhhan.example.common.domain.shop;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2021-04-22
 * Github : http://github.com/bhhan5274
 */

@Getter
@Setter
@NoArgsConstructor
public class OptionGroup {
    private String name;
    private List<Option> options;

    @Builder
    public OptionGroup(String name, List<Option> options){
        this.name = name;
        this.options = options;
    }
}
