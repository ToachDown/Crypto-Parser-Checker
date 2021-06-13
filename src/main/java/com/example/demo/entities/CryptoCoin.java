package com.example.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CryptoCoin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private double price;
    private double amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "crypto_exchange_id")
    private CryptoExchange cryptoExchange;

    public CryptoCoin(String name, double price, double amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CryptoCoin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price + '\'' +
                ", amount=" + amount +
                '}';
    }
}
