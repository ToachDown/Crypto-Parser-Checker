package com.example.demo.dto.binance;

import java.util.List;

public record BinanceDto(
        String code,
        String message,
        String messageDetails,
        List<BinanceCoinDto> data) {
}
