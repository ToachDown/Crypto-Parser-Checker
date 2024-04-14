package com.example.demo.clients.impl;

import com.example.demo.clients.ExchangeClient;
import com.example.demo.dto.ExchangeResponse;
import com.example.demo.dto.binance.BinanceCoinDto;
import com.example.demo.dto.binance.BinanceDto;
import com.example.demo.model.CryptoExchange;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BinanceClient extends ExchangeClient<BinanceCoinDto, BinanceDto> {
    public BinanceClient(RestTemplate template) {
        super(template, BinanceDto.class);
    }
}
