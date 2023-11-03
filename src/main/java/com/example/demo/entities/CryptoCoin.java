package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CryptoCoin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private double price;
    private double dayChange;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "crypto_exchange_id")
    private CryptoExchange cryptoExchange;

}
