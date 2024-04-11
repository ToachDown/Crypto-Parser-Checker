package com.example.demo.services.exchanges.impl;

import com.example.demo.clients.KucoinClient;
import com.example.demo.dto.kucoin.KucoinDto;
import com.example.demo.services.exchanges.ExchangeJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KucoinJob implements ExchangeJob {

    private final KucoinClient client;

    @Override
    @Scheduled(cron = "* */5 * * * *")
    public void loadCoinsData() {
        long startTime = System.currentTimeMillis();
        log.info("Start parsing Kucoin");
        KucoinDto kucoinData = client.getCoins();
        log.info("End parsing Kucoin by {} ms, parsed {} coins", (System.currentTimeMillis() - startTime), kucoinData.data().size());
    }
}
