package com.example.demo.services.job.impl;

import com.example.demo.clients.ExchangeClient;
import com.example.demo.dto.binance.BinanceCoinDto;
import com.example.demo.dto.binance.BinanceDto;
import com.example.demo.mappers.BinanceCoinMapper;
import com.example.demo.model.CryptoCoin;
import com.example.demo.model.enums.ExchangeName;
import com.example.demo.repo.CryptoCoinRepository;
import com.example.demo.repo.CryptoExchangeRepository;
import com.example.demo.services.job.ExchangeJob;
import org.springframework.stereotype.Service;

@Service
public class BinanceJob extends ExchangeJob<BinanceCoinDto, CryptoCoin, BinanceDto> {

    public BinanceJob(ExchangeClient<BinanceCoinDto, BinanceDto> client,
                      BinanceCoinMapper mapper,
                      CryptoCoinRepository coinRepository,
                      CryptoExchangeRepository cryptoExchangeRepository) {
        super(client, mapper, coinRepository, cryptoExchangeRepository);
    }

    @Override
    protected String getExchangeName() {
        return ExchangeName.BINANCE.getExchangeName();
    }
}
