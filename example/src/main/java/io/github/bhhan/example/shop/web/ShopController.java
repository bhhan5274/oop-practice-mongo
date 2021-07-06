package io.github.bhhan.example.shop.web;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.shop.usecase.ShopService;
import io.github.bhhan.example.shop.usecase.dto.ShopDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/shops")
public class ShopController {
    private final ShopService shopService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addShop(@RequestBody ShopDto.ShopReq shopReq){
        return shopService.addShop(shopReq);
    }

    @GetMapping("/{shopId}")
    @ResponseStatus(HttpStatus.OK)
    public ShopDto.ShopRes findById(@PathVariable String shopId){
        return shopService.findShopResByShopId(shopId);
    }

    @GetMapping("/{shopId}/status")
    @ResponseStatus(HttpStatus.OK)
    public boolean isOpen(@PathVariable String shopId){
        return shopService.isOpen(shopId);
    }

    @PutMapping("/{shopId}/open")
    @ResponseStatus(HttpStatus.OK)
    public void open(@PathVariable String shopId){
        shopService.shopOpen(shopId);
    }

    @PutMapping("/{shopId}/close")
    @ResponseStatus(HttpStatus.OK)
    public void close(@PathVariable String shopId){
        shopService.shopClose(shopId);
    }

    @PutMapping("/{shopId}/validOrderAmount")
    @ResponseStatus(HttpStatus.OK)
    public boolean isValidOrderAmount(@PathVariable String shopId, @RequestBody Money amount){
        return shopService.isValidOrderAmount(shopId, amount);
    }

    @PutMapping("/{shopId}/calculateCommissionFee")
    @ResponseStatus(HttpStatus.OK)
    public Money calculateCommissionFee(@PathVariable String shopId, @RequestBody Money amount){
        return shopService.calculateCommissionFee(shopId, amount);
    }
}
