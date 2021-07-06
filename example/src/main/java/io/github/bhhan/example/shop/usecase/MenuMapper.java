package io.github.bhhan.example.shop.usecase;

import io.github.bhhan.example.shop.domain.Menu;
import io.github.bhhan.example.shop.domain.OptionGroupSpecification;
import io.github.bhhan.example.shop.domain.OptionSpecification;
import io.github.bhhan.example.shop.usecase.dto.MenuDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MenuMapper {

    public MenuDto.MenuRes fromMenu(Menu menu){
        return MenuDto.MenuRes
                .builder()
                .menu(menu)
                .build();
    }

    public Menu fromMenuReq(MenuDto.MenuReq menuReq){
        return Menu
                .builder()
                .name(menuReq.getName())
                .description(menuReq.getDescription())
                .shopId(menuReq.getShopId())
                .basic(fromOptionGroupSpecificationReq(menuReq.getBasic()))
                .optionGroupSpecs(menuReq.getAdditives()
                    .stream()
                    .map(this::fromOptionGroupSpecificationReq)
                    .collect(Collectors.toList()))
                .build();
    }

    private OptionGroupSpecification fromOptionGroupSpecificationReq(MenuDto.OptionGroupSpecificationReq req){
        return OptionGroupSpecification
                .builder()
                .name(req.getName())
                .basic(req.isBasic())
                .exclusive(req.isExclusive())
                .optionSpecs(req.getOptionSpecs()
                    .stream()
                    .map(this::fromOptionSpecificationReq)
                    .collect(Collectors.toList()))
                .build();
    }

    private OptionSpecification fromOptionSpecificationReq(MenuDto.OptionSpecificationReq req){
        return OptionSpecification
                .builder()
                .name(req.getName())
                .price(req.getPrice())
                .build();
    }
}
