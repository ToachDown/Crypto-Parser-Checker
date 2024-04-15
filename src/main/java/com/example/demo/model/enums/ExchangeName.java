package com.example.demo.model.enums;

import lombok.Getter;

@Getter
public enum ExchangeName {
    BINANCE("binance"),
    KUCOIN("kucoin");

    private final String exchangeName;

    ExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }
}
