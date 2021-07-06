package com.example.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

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
    private double dayChange;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "crypto_exchange_id")
    private CryptoExchange cryptoExchange;

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cryptoExchange);
    }

    @Override
    public String toString() {
        return "CryptoCoin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price + '\'' +
                ", day Change=" + dayChange + '\'' +
                '}';
    }
}
