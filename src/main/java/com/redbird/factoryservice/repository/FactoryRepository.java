package com.redbird.factoryservice.repository;

import com.redbird.factoryservice.model.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactoryRepository extends JpaRepository<Factory, Long> {
    Factory findByName(String name);
}
