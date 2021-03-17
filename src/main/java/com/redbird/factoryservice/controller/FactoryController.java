package com.redbird.factoryservice.controller;

import com.redbird.factoryservice.client.ShopsClient;
import com.redbird.factoryservice.model.*;
import com.redbird.factoryservice.service.FactoryService;
import com.redbird.factoryservice.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/factory")
public class FactoryController {

    @Autowired
    private ShopsClient shopsClient;

    @Autowired
    private FactoryService factoryService;

    @Autowired
    private GoodService goodService;
//    private void Produce;

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        factoryService.deleteById(id);
    }

    @PostMapping
    public List<GoodInfo> registerFactory(@RequestBody FactoryDTO factoryDTO) {

        Factory factory = factoryService.findByName(factoryDTO.getFactoryName());
        List<GoodInfo> goodInfoList = new ArrayList<>();
        if (factory == null) {
            factory = new Factory();
            factory.setName(factoryDTO.getFactoryName());
            factory = factoryService.saveFactory(factory);
        }

        for (GoodInfoDTO goodInfoDTO : factoryDTO.getGoodInfoDTOList()) {
            GoodInfo goodInfo = goodService.findGood(goodInfoDTO.getName(),
                    goodInfoDTO.getShopName(), factory.getName());

            if (goodInfo == null && isInEnum(goodInfoDTO.getCategory(), Category.class)) {
                goodInfo = convertToGoodInfo(goodInfoDTO, factory);
                goodInfoList.add(goodService.saveGood(goodInfo));
            }
        }

        for (GoodInfo goodInfo : goodInfoList) {
            Thread producer = new Thread(new Producer(goodInfo));
            producer.start();
        }

        return goodInfoList;
    }

    private class Producer implements Runnable {
        private GoodFromFactory gff;
        GoodInfo goodInfo;

        public Producer(GoodInfo goodInfo) {
            this.goodInfo = goodInfo;
        }

        public void run() {
            try {
                Thread.sleep(goodInfo.getTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            GoodInfo tmp = goodService.findById(goodInfo.getId());
//            tmp.setAmount(tmp.getAmount()+1);
//            goodService.saveGood(tmp);
            GoodInfo send = goodService.findById(goodInfo.getId());
            send.setAmount(send.getAmount() + 1);
            gff = convertToGFF(send);
            try {
                if (shopsClient.saveGood(gff) != null) {
                    send.setAmount(send.getAmount() - gff.getAmount());
                }
            } finally {
                goodService.saveGood(send);
                this.run();
            }
        }
    }

    @PostConstruct
    private void startWorking() {
        List<GoodInfo> goodInfoList = goodService.findAll();

        goodInfoList.forEach(goodInfo -> {
            Thread producer = new Thread(new Producer(goodInfo));
            producer.start();
        });
    }

    private GoodInfo copyGI(GoodInfo goodInfo) {
        GoodInfo good = new GoodInfo();
        good.setShopName(goodInfo.getShopName());
        good.setTimeMillis(goodInfo.getTimeMillis());
        good.setAmount(goodInfo.getAmount());
        good.setFactory(goodInfo.getFactory());
        good.setDescription(goodInfo.getDescription());
        good.setName(goodInfo.getName());
        good.setId(goodInfo.getId());
        good.setCategory(goodInfo.getCategory());
        return good;
    }

    private GoodInfo convertToGoodInfo(GoodInfoDTO goodInfoDTO, Factory factory) {
        GoodInfo goodInfo = new GoodInfo();
        goodInfo.setName(goodInfoDTO.getName());
        goodInfo.setAmount(0L);
        goodInfo.setCategory(goodInfoDTO.getCategory());
        goodInfo.setDescription(goodInfoDTO.getDescription());
        goodInfo.setFactory(factory);
        goodInfo.setTimeMillis(goodInfoDTO.getTimeMillis());
        goodInfo.setShopName(goodInfoDTO.getShopName());
        return goodInfo;
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

//    private List<GoodResponse> convertToGRList(List<GoodInfo> goodInfoList) {
//        if (goodInfoList == null) return null;
//        List<GoodResponse> goodResponseList = new ArrayList<>();
//        goodInfoList.forEach(goodInfo -> {
//            goodResponseList.add(convertToGoodResp(goodInfo));
//        });
//        return goodResponseList;
//    }
//
//    private GoodResponse convertToGoodResp(GoodInfo goodInfo) {
//        if (goodInfo == null) return null;
//        GoodResponse goodResponse = new GoodResponse();
//        goodResponse.setName(goodInfo.getName());
//        goodResponse.setDescription(goodInfo.getDescription());
//        goodResponse.setAmount(goodInfo.getAmount());
//        goodResponse.setShopName(goodInfo.getShopName());
//        goodResponse.setCategory(goodInfo.getCategory());
//        goodResponse.setTimeMillis(goodInfo.getTimeMillis());
//        return goodResponse;
//    }

    public <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if(e.name().equals(value)) { return true; }
        }
        return false;
    }
}


