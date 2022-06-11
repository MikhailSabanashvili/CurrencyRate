package com.company.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan("com.company")
class GiffGiverTest {
    @MockBean
    CurrencyRateClient currencyRateClient;
    @Autowired
    ApplicationContext context;
    @MockBean
    GiffGiverClient giffGiverClient;

    @Test
    void getGiff() {
        Mockito.when(currencyRateClient.getCurrencyRateToday("code", "token")).thenReturn("60");
        Mockito.when(currencyRateClient.getCurrencyRateYesterday("code", "token", "yesterday"))
                .thenReturn("57");

        HashMap<String, Boolean> map = new HashMap<>();
        map.put("isBroke", true);
        ResponseEntity<Map> entity = new ResponseEntity<>(map, HttpStatus.OK);

        Mockito.when(giffGiverClient.getRandomGif("apiKey", "broke")).thenReturn(entity);
        CurrencyRateClient beanCurrency = context.getBean(CurrencyRateClient.class);
        String today = beanCurrency.getCurrencyRateToday("code", "token");
        String yesterday = beanCurrency.getCurrencyRateYesterday("code", "token", "yesterday");

        GiffGiverClient beanGiff = context.getBean(GiffGiverClient.class);
        ResponseEntity<Map> e = null;
        if(Double.parseDouble(today) > Double.parseDouble(yesterday)) {
           e = beanGiff.getRandomGif("apiKey", "broke");
        }
        assert e != null;
        Assert.isTrue(true,
                String.valueOf(Boolean.parseBoolean(Objects.requireNonNull(e.getBody()).get("isBroke").toString())));
    }

}