package com.redbird.factoryservice.model;

import lombok.Data;

import java.util.List;

@Data
public class FactoryDTO {
    String factoryName;
    List<GoodInfoDTO> goodInfoDTOList;
}
