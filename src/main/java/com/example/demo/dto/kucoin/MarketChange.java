package com.example.demo.dto.kucoin;

import java.math.BigDecimal;

public record MarketChange(
        BigDecimal low,
        BigDecimal high,
        BigDecimal open,
        BigDecimal changeRate,
        BigDecimal changePrice,
        BigDecimal vol,
        BigDecimal volValue
) {
}
