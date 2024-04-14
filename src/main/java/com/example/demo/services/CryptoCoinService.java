package com.example.demo.services;

import com.example.demo.model.CryptoCoin;
import com.example.demo.model.CryptoExchange;
import com.example.demo.model.enums.CoinState;
import com.example.demo.repo.CryptoCoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CryptoCoinService {

    private final CryptoCoinRepository coinRepository;

    public Map<CryptoExchange, List<CryptoCoin>> getNewStateGroupedCoins() {
        return coinRepository.findCryptoCoinByState(CoinState.NEW.name())
                .stream()
                .collect(Collectors.groupingBy(CryptoCoin::getCryptoExchange));
    }
}
