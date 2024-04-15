package com.example.demo.services.job.impl;

import com.example.demo.clients.ExchangeClient;
import com.example.demo.dto.kucoin.KucoinCoinDto;
import com.example.demo.dto.kucoin.KucoinDto;
import com.example.demo.mappers.KucoinCoinMapper;
import com.example.demo.model.CryptoCoin;
import com.example.demo.model.enums.ExchangeName;
import com.example.demo.repo.CryptoCoinRepository;
import com.example.demo.repo.CryptoExchangeRepository;
import com.example.demo.services.job.ExchangeJob;
import org.springframework.stereotype.Service;

@Service
public class KucoinJob extends ExchangeJob<KucoinCoinDto, CryptoCoin, KucoinDto> {

    public KucoinJob(ExchangeClient<KucoinCoinDto, KucoinDto> client,
                     KucoinCoinMapper mapper,
                     CryptoCoinRepository coinRepository,
                     CryptoExchangeRepository cryptoExchangeRepository) {
        super(client, mapper, coinRepository, cryptoExchangeRepository);
    }

    @Override
    protected String getExchangeName() {
        return ExchangeName.KUCOIN.getExchangeName();
    }
}
