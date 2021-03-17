package com.redbird.factoryservice.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class GoodInfoDTO {

    private String name;
    private String description;
    private String shopName;
    private String category;
    private Long timeMillis;
}
