package com.redbird.factoryservice.model;

import lombok.Data;

import java.util.List;

@Data
public class FactoryDTO {
    String name;
    List<GoodInfo> goodInfoList;
}
