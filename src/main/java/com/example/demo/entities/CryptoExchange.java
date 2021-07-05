package com.example.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class CryptoExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String url;

    @OneToMany(mappedBy = "cryptoExchange",fetch = FetchType.EAGER)
    private Set<CryptoCoin> coins;

    @Override
    public String toString() {
        return "CryptoExchange{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", coins=" + coins +
                '}';
    }
}
