package com.example.demo.services;

import com.example.demo.entities.CryptoCoin;
import com.example.demo.entities.CryptoExchange;
import com.example.demo.repositories.CoinRepository;
import com.example.demo.repositories.ExchangeRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

@Service
public class CoinService {

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private CoinRepository coinRepository;

    public List<CryptoExchange> getCoins() throws IOException {
        List<CryptoExchange> cryptoExchanges = exchangeRepository.findAll();
        List<CryptoExchange> newListExchanges = new ArrayList<>();
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
            newListExchanges.add(cryptoExchange);
        }
        return newListExchanges;
    }

    private CryptoExchange getCoinBinance(CryptoExchange cryptoExchange,JsonObject jsonObject){
        JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();
        Set<CryptoCoin> list = new HashSet<>();
        for (JsonElement jsonElement : jsonArray) {
            CryptoCoin cryptoCoin = new CryptoCoin();
            if (!jsonElement.getAsJsonObject().get("name").isJsonNull()) {
                cryptoCoin.setName(jsonElement.getAsJsonObject().get("name").getAsString());
            }
            if(!jsonElement.getAsJsonObject().get("price").isJsonNull()) {
                cryptoCoin.setPrice(jsonElement.getAsJsonObject().get("price").getAsDouble());
            }
            if(!jsonElement.getAsJsonObject().get("circulatingSupply").isJsonNull()) {
                cryptoCoin.setAmount(jsonElement.getAsJsonObject().get("circulatingSupply").getAsDouble());
            }
            list.add(cryptoCoin);
        }
        coinRepository.saveAll(list);
        cryptoExchange.setCoins(list);
        exchangeRepository.save(cryptoExchange);
        return cryptoExchange;
    }

    private CryptoExchange getCoinKraken(CryptoExchange cryptoExchange, JsonObject jsonObject){
        JsonArray jsonArray = jsonObject.get("result").getAsJsonArray();
        Set<CryptoCoin> cryptoCoins = new HashSet<>();
        for (JsonElement jsonElement : jsonArray) {
            CryptoCoin cryptoCoin = new CryptoCoin();
            if(!jsonElement.getAsJsonObject().get("asset").isJsonNull()){
                cryptoCoin.setName(jsonElement.getAsJsonObject().get("asset").getAsString());
            }
            if(!jsonElement.getAsJsonObject().get("price").isJsonNull()){
                cryptoCoin.setPrice(jsonElement.getAsJsonObject().get("price").getAsDouble());
            }
            if(!jsonElement.getAsJsonObject().get("liquidity").getAsJsonObject().get("ask").isJsonNull()){
                cryptoCoin.setAmount(jsonElement.getAsJsonObject().get("liquidity").getAsJsonObject().get("ask").getAsDouble());
            }
            cryptoCoins.add(cryptoCoin);
        }
        coinRepository.saveAll(cryptoCoins);
        cryptoExchange.setCoins(cryptoCoins);
        exchangeRepository.save(cryptoExchange);
        return cryptoExchange;
    }

    private CryptoExchange getCoinKuCoin(CryptoExchange cryptoExchange, JsonObject jsonObject){
        JsonObject jsonArray = jsonObject.get("data").getAsJsonObject();
        Set<String> map = jsonArray.keySet();
        Set<CryptoCoin> cryptoCoins = new HashSet<>();
        for (String s : map) {
            CryptoCoin cryptoCoin = new CryptoCoin();
            cryptoCoin.setName(s);
            if(!jsonArray.get(s).isJsonNull()){
                cryptoCoin.setPrice(jsonArray.get(s).getAsDouble());

            }
            cryptoCoins.add(cryptoCoin);
        }
        coinRepository.saveAll(cryptoCoins);
        cryptoExchange.setCoins(cryptoCoins);
        exchangeRepository.save(cryptoExchange);
        return cryptoExchange;
    }
}
