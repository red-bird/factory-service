package com.redbird.factoryservice.service;

import com.redbird.factoryservice.model.Factory;
import com.redbird.factoryservice.repository.FactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactoryService {

    @Autowired
    FactoryRepository factoryRepository;

    public Factory findByName(String name) {
        return factoryRepository.findByName(name);
    }
    
    public void deleteById(Long id) {
        factoryRepository.deleteById(id);
    }

    public Factory saveFactory(Factory factory) {
        return factoryRepository.save(factory);
    }
}
