package com.company.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "GiffGiverClient", url = "https://api.giphy.com/v1/gifs/")
public interface GiffGiverClient {
    @GetMapping("/random")
    ResponseEntity<Map> getRandomGif(
            @RequestParam("apiKey") String apiKey,
            @RequestParam("tag") String tag
    );

}
