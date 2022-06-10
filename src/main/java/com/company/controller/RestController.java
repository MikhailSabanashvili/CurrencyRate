package com.company.controller;

import com.company.service.GiffGiver;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@AllArgsConstructor
public class RestController {
    private GiffGiver giffGiver;

    @GetMapping("/get/{code}")
    public ResponseEntity<Map> getGif(@PathVariable String code) {
        /**
         * 1. Дергаем курс валют(REST https://docs.openexchangerates.org/), получаем ответ
         * 2. Если валюта/доллар > чем такое же соотношение вчера, то идем в
         * https://giphy.com/search/rich и берем рандомную гифку и отдаем ее клиенту
         * иначе тоже самое с
         * https://giphy.com/search/broke
         * REST гифок https://developers.giphy.com/docs/api
         * 3. для взаимодействия с внешними сервисами(гифки, курс валют) - юзать Feign
         *
         * my app_id 9bf3134faf914c27b33f4050089796a3
         */
        return giffGiver.getGiff(code);
    }

}
