package com.example.demo.services.job;

import com.example.demo.clients.ExchangeClient;
import com.example.demo.dto.CoinDto;
import com.example.demo.dto.ExchangeResponse;
import com.example.demo.mappers.CryptoCoinMapper;
import com.example.demo.model.CryptoCoin;
import com.example.demo.model.CryptoExchange;
import com.example.demo.model.enums.CoinState;
import com.example.demo.repo.CryptoCoinRepository;
import com.example.demo.repo.CryptoExchangeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public abstract class ExchangeJob<T extends CoinDto, F extends CryptoCoin, E extends ExchangeResponse<T>>{

    protected final ExchangeClient<T, E> client;
    protected final CryptoCoinMapper<T, F> mapper;
    private final CryptoCoinRepository coinRepository;
    private final CryptoExchangeRepository cryptoExchangeRepository;

    public void loadCoinsData() {
        long startTime = System.currentTimeMillis();
        CryptoExchange exchange = cryptoExchangeRepository.findCryptoExchangeByName(getExchangeName());

        log.info("Start parsing {}", exchange.getName());

        E exchangeData = client.getCoins(exchange);

        log.info("End parsing {} by {} ms, parsed {} coins", exchange.getName(), (System.currentTimeMillis() - startTime), exchangeData.getData().size());
        List<F> coins = exchangeData.getData()
                .stream()
                .map(mapper::convertToModel)
                .peek(coin -> initCoin(coin, exchange))
                .toList();
        List<CryptoCoin> oldCoins = coinRepository.findCryptoCoinByState(CoinState.NEW.name())
                .stream()
                .peek(coin -> coin.setState(CoinState.OLD.name()))
                .toList();
        coinRepository.saveAll(oldCoins);
        coinRepository.saveAll(coins);
        coinRepository.flush();
    }

    protected void initCoin(CryptoCoin coin, CryptoExchange exchange) {
        coin.setCryptoExchange(exchange);
        coin.setState(CoinState.NEW.name());
    }

    protected abstract String getExchangeName();
}
