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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@EnableScheduling
public class AutoUpdateDBService {

    @Value("${commision.taker.binance}")
    private double takerBinance;

    @Value("${commision.maker.binance}")
    private double makerBinance;

    @Value("${commision.taker.kucoin}")
    private double takerKuCoin;

    @Value("${commision.maker.kucoin}")
    private double makerKuCoin;

    @Value("${commision.const.kucoin}")
    private double constKuCoin;

    @Value("${commision.maker.kraken}")
    private double makerKraken;

    @Value("${commision.instant.kraken}")
    private double instantByCardKraken;

    @Value("${commision.const.kraken}")
    private double constKraken;

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private CoinRepository coinRepository;

    @Scheduled(fixedRate = 20000)
    public void updateDatabase() throws IOException {
        List<CryptoExchange> cryptoExchanges = exchangeRepository.findAll();
        for (CryptoExchange cryptoExchange : cryptoExchanges) {
            URL url = new URL(cryptoExchange.getUrl());
            URLConnection request = url.openConnection();
            request.connect();
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader(request.getInputStream()));
            JsonObject jsonObject = root.getAsJsonObject();
            switch(cryptoExchange.getName()) {
                case "binance": {
                    getCoinBinance(cryptoExchange, jsonObject);
                    break;
                }
                case "kraken": {
                    getCoinKraken(cryptoExchange, jsonObject);
                    break;
                }
                case "kucoin": {
                    getCoinKuCoin(cryptoExchange, jsonObject);
                    break;
                }
                default:{
                    break;
                }
            }
        }
    }

    private void getCoinBinance(CryptoExchange cryptoExchange,JsonObject jsonObject){
        JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();
        Set<CryptoCoin> list = new HashSet<>();
        for (JsonElement jsonElement : jsonArray) {
            CryptoCoin cryptoCoin = null;
            if (!jsonElement.getAsJsonObject().get("name").isJsonNull()) {
                if(!jsonElement.getAsJsonObject().get("name").getAsString().contains("DOWN") &&
                        !jsonElement.getAsJsonObject().get("name").getAsString().contains("UP")) {
                    if (coinRepository.findByName(jsonElement.getAsJsonObject().get("name").getAsString()) == null) {
                        cryptoCoin = new CryptoCoin();
                    } else {
                        cryptoCoin = coinRepository.findByName(jsonElement.getAsJsonObject().get("name").getAsString());
                    }
                    cryptoCoin.setName(jsonElement.getAsJsonObject().get("name").getAsString());
                }
            }
            if (!jsonElement.getAsJsonObject().get("price").isJsonNull() && cryptoCoin != null) {
                cryptoCoin.setPrice(jsonElement.getAsJsonObject().get("price").getAsDouble()*makerBinance*takerBinance);
            }
            if (cryptoCoin != null) {
                cryptoCoin.setCryptoExchange(cryptoExchange);
                list.add(cryptoCoin);
            }
        }
        coinRepository.saveAll(list);
        cryptoExchange.setCoins(list);
        exchangeRepository.save(cryptoExchange);
    }

    private void getCoinKraken(CryptoExchange cryptoExchange, JsonObject jsonObject){
        JsonArray jsonArray = jsonObject.get("result").getAsJsonArray();
        Set<CryptoCoin> cryptoCoins = new HashSet<>();
        for (JsonElement jsonElement : jsonArray) {
            CryptoCoin cryptoCoin = null;
            if(!jsonElement.getAsJsonObject().get("asset").isJsonNull()){
                if(coinRepository.findByName(jsonElement.getAsJsonObject().get("asset").getAsString()) == null){
                    cryptoCoin = new CryptoCoin();
                } else {
                    cryptoCoin = coinRepository.findByName(jsonElement.getAsJsonObject().get("asset").getAsString());
                }
                cryptoCoin.setName(jsonElement.getAsJsonObject().get("asset").getAsString());
            }
            if(!jsonElement.getAsJsonObject().get("price").isJsonNull() && cryptoCoin != null){
                cryptoCoin.setPrice(jsonElement.getAsJsonObject()
                        .get("price").getAsDouble()*takerKuCoin*makerKuCoin+coinRepository
                        .findByName("BTC").getPrice()*constKuCoin);
            }
            if(cryptoCoin != null) {
                cryptoCoin.setCryptoExchange(cryptoExchange);
                cryptoCoins.add(cryptoCoin);
            }
        }
        coinRepository.saveAll(cryptoCoins);
        cryptoExchange.setCoins(cryptoCoins);
        exchangeRepository.save(cryptoExchange);
    }

    private void getCoinKuCoin(CryptoExchange cryptoExchange, JsonObject jsonObject){
        JsonObject jsonArray = jsonObject.get("data").getAsJsonObject();
        Set<String> map = jsonArray.keySet();
        Set<CryptoCoin> cryptoCoins = new HashSet<>();
        for (String s : map) {
            CryptoCoin cryptoCoin = null;
            if (!s.contains("3")) {
                if (coinRepository.findByName(s) == null) {
                    cryptoCoin = new CryptoCoin();
                } else {
                    cryptoCoin = coinRepository.findByName(s);
                }
                cryptoCoin.setName(s);

                if(!jsonArray.get(s).isJsonNull() && cryptoCoin != null){
                    cryptoCoin.setPrice(jsonArray.get(s).getAsDouble()*instantByCardKraken*makerKraken+constKraken);
                }
                if (cryptoCoin != null) {
                    cryptoCoin.setCryptoExchange(cryptoExchange);
                    cryptoCoins.add(cryptoCoin);
                }
            }
        }
        coinRepository.saveAll(cryptoCoins);
        cryptoExchange.setCoins(cryptoCoins);
        exchangeRepository.save(cryptoExchange);
    }


}
