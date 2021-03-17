package com.redbird.factoryservice.controller;

import com.redbird.factoryservice.model.GoodInfo;
import com.redbird.factoryservice.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/factory/goods")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @GetMapping
    public List<GoodInfo> findAll() {
        return goodService.findAll();
    }

    @GetMapping("/{id}")
    public GoodInfo findById(@PathVariable("id") Long id) {
        return goodService.findById(id);
    }
}
