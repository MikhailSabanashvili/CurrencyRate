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
        return giffGiver.getGiff(code);
    }

}
