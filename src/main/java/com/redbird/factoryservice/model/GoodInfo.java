package com.redbird.factoryservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "goods_info")
public class GoodInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Long amount;
    private String shopName;
    private String category;
    private Long timeMillis;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "factory_id")
    private Factory factory;
}
