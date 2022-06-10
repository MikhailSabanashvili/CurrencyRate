package com.company.service;

import com.company.helper.ConfigProperties;
import com.company.helper.RegexExtractor;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.company.constants.RegularExpressions.CURRENCY_REGEX;

@Service
@AllArgsConstructor
public class GiffGiver {
    private CurrencyRateClient currencyRateClient;
    private RegexExtractor extractor;
    private ConfigProperties configProperties;
    private GiffGiverClient giffGiverClient;

    public ResponseEntity<Map> getGiff(String code) {
        String currencyToday = getCurrencyRate(code, true);
        String currencyYesterday = getCurrencyRate(code, false);

        ResponseEntity<Map> result;
        if(Double.parseDouble(currencyToday) > Double.parseDouble(currencyYesterday)) {
            result = giffGiverClient.getRandomGif(configProperties.getGiffToken(), configProperties.getTagRich());
        } else {
            result = giffGiverClient.getRandomGif(configProperties.getGiffToken(), configProperties.getTagBroke());
        }
        return result;
    }

    private String getCurrencyRate(String code, boolean isToday) {
        String rate;
        if(isToday) {
            rate = currencyRateClient.getCurrencyRateToday(code, configProperties.getExchangeToken());
        } else {
            LocalDateTime ldt = LocalDateTime.now().minusDays(1);
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = format.format(ldt);
            rate = currencyRateClient.getCurrencyRateYesterday(code,
                    configProperties.getExchangeToken(), date);
        }

        String regex = extractor.createRegex(code, CURRENCY_REGEX);
        return extractor.getFirst(rate, regex);
    }



}
