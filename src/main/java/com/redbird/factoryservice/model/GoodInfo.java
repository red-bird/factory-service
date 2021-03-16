package com.redbird.factoryservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "goods_info")
public class GoodInfo {
    private String name;
    private String description;
    private Long amount;
    private String shopName;
    private String category;
    private Long timeMillis;
    private Factory factory;
}
