package io.github.bhhan.example.billing.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BillingRepository extends MongoRepository<Billing, String> {
    Optional<Billing> findByShopId(String shopId);
}
