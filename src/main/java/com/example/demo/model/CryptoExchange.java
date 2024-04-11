package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoExchange {

    @Id
    @SequenceGenerator(name = "crypto_exchange_seq", sequenceName = "crypto_exchange_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crypto_exchange_seq")
    private long id;
    private String name;
    private String url;
}
