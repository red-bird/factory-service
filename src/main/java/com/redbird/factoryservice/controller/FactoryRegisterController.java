package com.redbird.factoryservice.controller;

import com.redbird.factoryservice.client.ShopsClient;
import com.redbird.factoryservice.model.*;
import com.redbird.factoryservice.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/factory")
public class FactoryRegisterController {

    @Autowired
    ShopsClient shopsClient;

    @Autowired
    FactoryService factoryService;
//    private void Produce;


    @PostMapping
    public FactoryResponse registerFactory(@RequestBody FactoryDTO factoryDTO) {
        Factory factory = factoryService.findByName(factoryDTO.getName());
        if (factory != null) {
            factoryDTO.getGoodInfoList().forEach(goodInfo -> {

            });
        }

        factory.getGoodInfoList().forEach(goodInfo -> {
            Thread producer = new Thread(new Producer(goodInfo));
            producer.start();
        });
    }

    private class Producer implements Runnable {
        private final Long timeMillis;
        private GoodFromFactory gff;

        public Producer(GoodInfo goodInfo) {
            timeMillis = goodInfo.getTimeMillis();
            gff = convertToGFF(goodInfo);
        }

        public void run() {
            try {
                Thread.sleep(timeMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            shopsClient.saveGood(gff);
        }
    }

    private GoodFromFactory convertToGFF(GoodInfo goodInfo) {
        if (goodInfo == null) return null;
        GoodFromFactory gff = new GoodFromFactory();
        gff.setName(goodInfo.getName());
        gff.setDescription(goodInfo.getDescription());
        gff.setAmount(goodInfo.getAmount());
        gff.setShopName(goodInfo.getShopName());
        gff.setCategory(goodInfo.getCategory());
        return gff;
    }

    private List<GoodResponse> convertToGRList(List<GoodInfo> goodInfoList) {
        if (goodInfoList == null) return null;
        List<GoodResponse> goodResponseList = new ArrayList<>();
        goodInfoList.forEach(goodInfo -> {
            goodResponseList.add(convertToGoodResp(goodInfo));
        });
        return goodResponseList;
    }

    private GoodResponse convertToGoodResp(GoodInfo goodInfo) {
        if (goodInfo == null) return null;
        GoodResponse goodResponse = new GoodResponse();
        goodResponse.setName(goodInfo.getName());
        goodResponse.setDescription(goodInfo.getDescription());
        goodResponse.setAmount(goodInfo.getAmount());
        goodResponse.setShopName(goodInfo.getShopName());
        goodResponse.setCategory(goodInfo.getCategory());
        goodResponse.setTimeMillis(goodInfo.getTimeMillis());
        return goodResponse;
    }

    public <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if(e.name().equals(value)) { return true; }
        }
        return false;
    }
}


