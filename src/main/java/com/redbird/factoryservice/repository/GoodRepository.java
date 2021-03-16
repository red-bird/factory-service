package com.redbird.factoryservice.repository;

import com.redbird.factoryservice.model.GoodInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodRepository extends JpaRepository <GoodInfo, Long> {
    GoodInfo findByNameAndDescriptionAndShopName
}
