package io.github.bhhan.example.shop.usecase;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.shop.domain.Shop;
import io.github.bhhan.example.shop.domain.ShopRepository;
import io.github.bhhan.example.shop.usecase.dto.ShopDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;

    public String addShop(ShopDto.ShopReq shopReq){
        Shop shop = shopMapper.fromShopReq(shopReq);
        return shopRepository.save(shop).getId();
    }

    public ShopDto.ShopRes findShopResByShopId(String shopId){
        return shopMapper.fromShop(findByShopId(shopId));
    }

    public boolean isValidOrderAmount(String shopId, Money amount){
        Shop shop = findByShopId(shopId);
        return shop.isValidOrderAmount(amount);
    }

    public void shopOpen(String shopId){
        Shop shop = findByShopId(shopId);
        shop.open();
        shopRepository.save(shop);
    }

    public void shopClose(String shopId){
        Shop shop = findByShopId(shopId);
        shop.close();
        shopRepository.save(shop);
    }

    public Money calculateCommissionFee(String shopId, Money price){
        return findByShopId(shopId)
                .calculateCommissionFee(price);
    }

    public boolean isOpen(String shopId){
        return findByShopId(shopId).isOpen();
    }

    private Shop findByShopId(String shopId){
        return shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid shopId"));
    }
}
