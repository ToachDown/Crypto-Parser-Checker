package com.example.demo.clients;

import com.example.demo.dto.CoinDto;
import com.example.demo.dto.ExchangeResponse;
import com.example.demo.model.CryptoExchange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public abstract class ExchangeClient<Coin extends CoinDto, ExchangeResp extends ExchangeResponse<Coin>>{

    private final RestTemplate template;
    private final Class<ExchangeResp> type;

    public ExchangeResp getCoins(CryptoExchange exchange) {
        log.info("send request to {}", exchange.getName());
        return template.getForEntity(exchange.getUrl(), type)
                .getBody();
    }
}
