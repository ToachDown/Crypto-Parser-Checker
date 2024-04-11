package com.example.demo.clients;

import com.example.demo.model.CryptoExchange;
import com.example.demo.dto.binance.BinanceDto;
import com.example.demo.repositories.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class BinanceClient {

    private final RestTemplate template;
    private final ExchangeRepository repository;
    private final static String BINANCE_NAME = "binance";
    public BinanceDto getCoins() {
        CryptoExchange binance = repository.findCryptoExchangeByName(BINANCE_NAME);
        return template.getForEntity(binance.getUrl(), BinanceDto.class)
                       .getBody();
    }
}
