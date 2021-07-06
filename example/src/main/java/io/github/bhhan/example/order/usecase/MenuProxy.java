package io.github.bhhan.example.order.usecase;

import io.github.bhhan.example.common.domain.shop.OptionGroup;
import io.github.bhhan.example.shop.web.MenuController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuProxy {
    private final MenuController menuController;

    public boolean validateOrder(String menuId, String menuName, List<OptionGroup> optionGroups){
        return menuController.validateOrder(menuId, menuName, optionGroups);
    }
}
