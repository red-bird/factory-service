package com.redbird.factoryservice.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class GoodResponse {
    private String name;
    private String description;
    private Long amount;
    private String shopName;
    private String category;
    private Long timeMillis;
}
