package com.redbird.factoryservice.model;

import lombok.Data;

@Data
public class GoodFromFactory {
    private String name;
    private String description;
    private Long amount;
    private String shopName;
    private String category;
}
