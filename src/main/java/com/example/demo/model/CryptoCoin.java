package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoCoin {

    @Id
    @SequenceGenerator(name = "crypto_coin_seq", sequenceName = "crypto_coin_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crypto_coin_seq")
    private long id;
    private String name; //kucoin - baseCurrency, binance - name
    private BigDecimal buyPrice; //binance - price, kucoin - buy
    private BigDecimal sellPrice; //binance - price, kucoin - sell
    private long totalSupply; // binance - totalSupply, kucoin - -
    private BigDecimal dayVolume; // kucoin - vol, binance - volume
    private BigDecimal marketCup; // binance - marketCap, kucoin - -
    private LocalDateTime created;
    // * 100 = day change percent
    private BigDecimal dayChange; // binance - dayChange, kucoin - changeRate
    private String sellCoin; // binance - quoteAsset, kucoin - quoteCurrency
    @ManyToOne
    @JoinColumn(name = "crypto_exchange_id", nullable = false)
    private CryptoExchange cryptoExchange;
}
