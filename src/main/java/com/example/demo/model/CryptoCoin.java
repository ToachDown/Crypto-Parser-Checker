package com.example.demo.model;

import com.example.demo.model.enums.CoinState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    private String name;
    private String state;
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;
    private long totalSupply;
    private BigDecimal dayVolume;
    private BigDecimal marketCup;
    @CreationTimestamp
    private LocalDateTime created;
    // * 100 = day change percent
    private BigDecimal dayChange;
    private String sellCoin;
    @ManyToOne
    @JoinColumn(name = "crypto_exchange_id", nullable = false)
    private CryptoExchange cryptoExchange;
}
