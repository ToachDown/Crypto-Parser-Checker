package com.example.demo.dto.kucoin;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public record KucoinCoinDto(
        String symbol,
        String symbolCode,
        BigDecimal buy,
        BigDecimal bidSize,
        BigDecimal sell,
        BigDecimal askSize,
        BigDecimal low,
        BigDecimal high,
        BigDecimal open,
        BigDecimal lastTradedPrice,
        BigDecimal changeRate,
        BigDecimal changePrice,
        MarketChange marketChange1h,
        MarketChange marketChange4h,
        MarketChange marketChange24h,
        Timestamp datetime,
        BigDecimal vol,
        BigDecimal volValue,
        BigDecimal close,
        String baseCurrency,
        String quoteCurrency,
        Boolean trading,
        Integer sort,
        Integer board,
        Integer mark,
        String market,
        List<String> markets,
        BigDecimal averagePrice,
        BigDecimal takerFeeRate,
        BigDecimal makerFeeRate,
        BigDecimal takerCoefficient,
        BigDecimal makerCoefficient,
        Boolean marginTrade
) {
}
