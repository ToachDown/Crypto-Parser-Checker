package com.example.demo.clients.impl;

import com.example.demo.clients.ExchangeClient;
import com.example.demo.dto.kucoin.KucoinCoinDto;
import com.example.demo.dto.kucoin.KucoinDto;
import com.example.demo.model.CryptoExchange;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KucoinClient extends ExchangeClient<KucoinCoinDto, KucoinDto> {
    public KucoinClient(RestTemplate template) {
        super(template, KucoinDto.class);
    }
}
