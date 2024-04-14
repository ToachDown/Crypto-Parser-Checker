package com.example.demo.dto;

import java.util.List;

public abstract class ExchangeResponse<Coin extends CoinDto> {
    public abstract List<Coin> getData();
}
