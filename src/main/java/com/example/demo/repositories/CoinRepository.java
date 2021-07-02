package com.example.demo.repositories;

import com.example.demo.entities.CryptoCoin;
import com.example.demo.entities.CryptoExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CoinRepository extends JpaRepository<CryptoCoin, Long> {

    public Set<CryptoCoin> findCryptoCoinsByCryptoExchangeOrderByPriceDesc(CryptoExchange cryptoExchange);

    public CryptoCoin findByName(String name);
}
