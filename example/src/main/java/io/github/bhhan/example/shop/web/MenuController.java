package io.github.bhhan.example.shop.web;

import io.github.bhhan.example.common.domain.shop.OptionGroup;
import io.github.bhhan.example.shop.domain.Menu;
import io.github.bhhan.example.shop.usecase.MenuService;
import io.github.bhhan.example.shop.usecase.dto.MenuDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.github.bhhan.example.shop.usecase.dto.MenuDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/menus")
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/{menuId}")
    @ResponseStatus(HttpStatus.OK)
    public MenuRes findById(@PathVariable String menuId){
        return menuService.findById(menuId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addMenu(@RequestBody MenuReq menuReq){
        return menuService.addMenu(menuReq);
    }

    @PostMapping("/{menuId}/validateOrder")
    @ResponseStatus(HttpStatus.OK)
    public boolean validateOrder(@PathVariable String menuId, @Param("name") String menuName, @RequestBody List<OptionGroup> optionGroups){
        try{
            menuService.validateOrder(menuId, menuName, optionGroups);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
