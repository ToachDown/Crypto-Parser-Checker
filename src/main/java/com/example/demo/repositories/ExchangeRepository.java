package com.example.demo.repositories;

import com.example.demo.model.CryptoExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface ExchangeRepository extends JpaRepository<CryptoExchange, Long>{

    CryptoExchange findCryptoExchangeByName(String exchangeName);
}
