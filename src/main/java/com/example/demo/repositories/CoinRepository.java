package com.example.demo.repositories;

import com.example.demo.entities.CryptoCoin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<CryptoCoin, Long> {
}
