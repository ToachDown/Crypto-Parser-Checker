package com.example.demo.repo;

import com.example.demo.model.CryptoCoin;
import com.example.demo.model.enums.CoinState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CryptoCoinRepository extends JpaRepository<CryptoCoin, Long> {

    List<CryptoCoin> findCryptoCoinByState(String state);
}
