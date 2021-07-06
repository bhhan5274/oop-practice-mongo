package io.github.bhhan.example.shop.usecase;

import io.github.bhhan.example.common.domain.shop.OptionGroup;
import io.github.bhhan.example.shop.domain.Menu;
import io.github.bhhan.example.shop.domain.MenuRepository;
import io.github.bhhan.example.shop.usecase.dto.MenuDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.github.bhhan.example.shop.usecase.dto.MenuDto.*;

@Transactional
@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    public String addMenu(MenuReq menuReq){
        return menuRepository.save(menuMapper.fromMenuReq(menuReq)).getId();
    }

    public void validateOrder(String menuId, String menuName, List<OptionGroup> optionGroups){
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid menuId"));

        menu.validateOrder(menuName, optionGroups);
    }

    public MenuRes findById(String menuId){
        return menuMapper.fromMenu(menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid menuId")));
    }
}
