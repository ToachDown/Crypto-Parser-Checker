package com.example.demo.config;

import com.example.demo.clients.ExchangeClient;
import com.example.demo.dto.CoinDto;
import com.example.demo.dto.binance.BinanceCoinDto;
import com.example.demo.dto.binance.BinanceDto;
import com.example.demo.mappers.CryptoCoinMapper;
import com.example.demo.model.CryptoCoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@EnableScheduling
public class ExchangeConfiguration {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
