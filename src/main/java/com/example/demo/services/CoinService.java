package com.example.demo.services;

import com.example.demo.entities.CryptoCoin;
import com.example.demo.entities.CryptoExchange;
import com.example.demo.repositories.CoinRepository;
import com.example.demo.repositories.ExchangeRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CoinService {

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private CoinRepository coinRepository;

    public CryptoCoin findMaximumParams(List<CryptoCoin> list){
            CryptoCoin idealCoin = new CryptoCoin();
            idealCoin.setPrice(999999);
            for (CryptoCoin cryptoCoin : list) {
                if(idealCoin.getPrice() > cryptoCoin.getPrice()){
                    idealCoin.setPrice(cryptoCoin.getPrice());
                }
                if(idealCoin.getDayChange() < cryptoCoin.getDayChange()){
                    idealCoin.setDayChange(cryptoCoin.getDayChange());
                }
            }
            return idealCoin;
    }

    public List<CryptoCoin> getCompareCoins(Map<String,String> form){
        List<CryptoCoin> result = new ArrayList<>();
        for (String s : form.keySet()) {
            List<CryptoCoin> cryptoCoin = coinRepository.findCryptoCoinByName(s.split("_")[0]);
            for (CryptoCoin coin : cryptoCoin) {
                if(coin.getCryptoExchange().getName().equals(s.split("_")[1])){
                    result.add(coin);
                }
            }
        }
        return result;
    }

    public List<CryptoExchange> getAllExchanges(String filter, Integer startPrice,Integer endPrice){
        List<CryptoExchange> resultList =  new ArrayList<>();
        for (CryptoExchange exchange : exchangeRepository.findAll()) {
            Set<CryptoCoin> coins = coinRepository.findCryptoCoinsByCryptoExchangeOrderByPriceDesc(exchange);
            if (!filter.equals("")) {
                coins = coins.stream().filter(x -> x.getName().contains(filter)).collect(Collectors.toSet());
            }
            if(startPrice != null && endPrice != null){
                coins = coins.stream()
                        .filter(x->x.getPrice()>startPrice && x.getPrice()<endPrice)
                        .collect(Collectors.toSet());
            }else if(startPrice != null){
                coins = coins.stream()
                        .filter(x->x.getPrice()>startPrice)
                        .collect(Collectors.toSet());
            } else if(endPrice != null){
                coins = coins.stream()
                        .filter(x->x.getPrice()<endPrice)
                        .collect(Collectors.toSet());
            }
            exchange.setCoins(coins);
            resultList.add(exchange);
        }
        return resultList;
    }

}
