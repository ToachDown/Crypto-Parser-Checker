package com.example.demo.controllers.rest;

import com.example.demo.model.CryptoCoin;
import com.example.demo.model.CryptoExchange;
import com.example.demo.services.CryptoCoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v2/crypto")
@RequiredArgsConstructor
public class CryptoCoinRestController {

    private final CryptoCoinService coinService;

    @GetMapping("/coin")
    public Map<CryptoExchange, List<CryptoCoin>> getGroupedCoins(){
        return coinService.getNewStateGroupedCoins();
    }
}
