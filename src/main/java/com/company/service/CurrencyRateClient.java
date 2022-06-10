package com.company.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "CurrencyRateClient", url = "https://openexchangerates.org/api/")
public interface CurrencyRateClient {
    @GetMapping("/latest.json?app_id=${exchange.token}")
    String getCurrencyRateToday(String code, @PathVariable String exchange);

    @GetMapping("/historical/{date}.json?app_id=${exchange.token}")
    String getCurrencyRateYesterday(String code, @PathVariable String exchange, @PathVariable String date);
}
