package com.redbird.factoryservice.model;

import lombok.Data;

@Data
public class GoodDTO {
    private String name;
    private String description;
    private Double cost;
    private Long amount;
    private String category;
}
