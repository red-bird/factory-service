package com.redbird.factoryservice.client;

import com.redbird.factoryservice.model.GoodDTO;
import com.redbird.factoryservice.model.GoodFromFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ShopsClientFallback implements FallbackFactory<ShopsClient> {
    @Override
    public ShopsClient create(Throwable cause) {
        return null;
    }
}
