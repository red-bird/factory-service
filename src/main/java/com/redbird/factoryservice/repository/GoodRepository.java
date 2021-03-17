package com.redbird.factoryservice.repository;

import com.redbird.factoryservice.model.Factory;
import com.redbird.factoryservice.model.GoodInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodRepository extends JpaRepository <GoodInfo, Long> {
    GoodInfo findByNameAndShopNameAndFactory(String name, String shopName, Factory factory);
}
