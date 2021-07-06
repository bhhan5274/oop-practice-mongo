package io.github.bhhan.example.shop.domain;

import io.github.bhhan.example.common.domain.shop.Option;
import io.github.bhhan.example.common.domain.shop.OptionGroup;
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
import java.util.stream.Collectors;

@Document(collection = "option_group_specs")
@Getter
@NoArgsConstructor
public class OptionGroupSpecification {
    @Id
    private String id;

    private String name;
    private boolean exclusive;
    private boolean basic;

    @DBRef
    @Cascade
    private List<OptionSpecification> optionSpecs = new ArrayList<>();

    @Builder
    public OptionGroupSpecification(String id, String name, boolean exclusive, boolean basic, Collection<OptionSpecification> optionSpecs){
        Objects.requireNonNull(optionSpecs, "optionSpecs is nonNull");

        this.id = id;
        this.name = name;
        this.exclusive = exclusive;
        this.basic = basic;
        this.optionSpecs.addAll(optionSpecs);
    }

    public boolean isSatisfiedBy(OptionGroup optionGroup){
        return isSatisfiedBy(optionGroup.getName(), optionGroup.getOptions());
    }

    private boolean isSatisfiedBy(String groupName, List<Option> options){
        if(!this.name.equals(groupName)){
            return false;
        }

        List<Option> satisfied = this.optionSpecs
                .stream()
                .flatMap(spec -> options
                        .stream()
                        .filter(spec::isSatisfiedBy))
                .collect(Collectors.toList());

        if(satisfied.isEmpty()){
            return false;
        }

        if(exclusive && satisfied.size() > 1){
            return false;
        }

        return satisfied.size() == options.size();
    }
}
