package com.example.demo.services.exchanges.impl;

import com.example.demo.clients.BinanceClient;
import com.example.demo.dto.binance.BinanceDto;
import com.example.demo.services.exchanges.ExchangeJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BinanceJob implements ExchangeJob {

    private final BinanceClient client;

    @Override
    @Scheduled(cron = "* */5 * * * *")
    public void loadCoinsData() {
        long startTime = System.currentTimeMillis();
        log.info("Start parsing Binance");
        BinanceDto binanceData = client.getCoins();
        log.info("End parsing Binance by {} ms, parsed {} coins", (System.currentTimeMillis() - startTime), binanceData.data().size());

    }

}
