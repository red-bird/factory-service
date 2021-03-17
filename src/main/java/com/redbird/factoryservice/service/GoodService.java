package com.redbird.factoryservice.service;

import com.redbird.factoryservice.model.Factory;
import com.redbird.factoryservice.model.GoodInfo;
import com.redbird.factoryservice.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodService {

    @Autowired
    GoodRepository goodRepository;

    @Autowired
    FactoryService factoryService;

    public List<GoodInfo> findAll() {
        return goodRepository.findAll();
    }

    public GoodInfo findById(Long id) {
        return goodRepository.findById(id).orElse(null);
    }

    public GoodInfo findGood(String name, String shop, String factory) {
        Factory res = factoryService.findByName(factory);
        if (res == null) return null;
        return goodRepository.findByNameAndShopNameAndFactory(name, shop, res);
    }

    public GoodInfo saveGood(GoodInfo goodInfo) {
        return goodRepository.save(goodInfo);
    }
}
