package io.github.bhhan.example.shop.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShopRepository extends MongoRepository<Shop, String> {
}
