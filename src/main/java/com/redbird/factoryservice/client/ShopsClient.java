package com.redbird.factoryservice.client;

import com.redbird.factoryservice.model.GoodDTO;
import com.redbird.factoryservice.model.GoodFromFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "shops-service", path = "/goods", fallbackFactory = ShopsClientFallback.class)
public interface ShopsClient {

    @PostMapping
    public GoodDTO saveGood(@RequestBody GoodFromFactory goodFromFactory);
}
