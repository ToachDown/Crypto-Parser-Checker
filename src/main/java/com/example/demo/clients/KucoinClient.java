package com.example.demo.clients;

import com.example.demo.model.CryptoExchange;
import com.example.demo.dto.kucoin.KucoinDto;
import com.example.demo.repositories.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KucoinClient {

    private final RestTemplate template;
    private final ExchangeRepository repository;
    private final static String KUCOIN_NAME = "kucoin";
    public KucoinDto getCoins() {
        CryptoExchange kucoin = repository.findCryptoExchangeByName(KUCOIN_NAME);
        return template.getForEntity(kucoin.getUrl(), KucoinDto.class)
                .getBody();
    }
}
