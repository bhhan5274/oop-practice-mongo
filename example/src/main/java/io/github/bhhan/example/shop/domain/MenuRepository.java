package io.github.bhhan.example.shop.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MenuRepository extends MongoRepository<Menu, String> {
    List<Menu> findByShopId(String shopId);
}
