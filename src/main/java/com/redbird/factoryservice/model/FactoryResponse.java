package com.redbird.factoryservice.model;

import lombok.Data;

import java.util.List;

@Data
public class FactoryResponse {
    String name;
    List<GoodInfo> goodInfoList;
}
