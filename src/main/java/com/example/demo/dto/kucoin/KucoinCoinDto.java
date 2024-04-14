package com.example.demo.dto.kucoin;

import com.example.demo.dto.CoinDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class KucoinCoinDto extends CoinDto {
    private String symbol;
    private String symbolCode;
    private BigDecimal buy;
    private BigDecimal bidSize;
    private BigDecimal sell;
    private BigDecimal askSize;
    private BigDecimal low;
    private BigDecimal high;
    private BigDecimal open;
    private BigDecimal lastTradedPrice;
    private BigDecimal changeRate;
    private BigDecimal changePrice;
    private MarketChange marketChange1h;
    private MarketChange marketChange4h;
    private MarketChange marketChange24h;
    private Timestamp datetime;
    private BigDecimal vol;
    private BigDecimal volValue;
    private BigDecimal close;
    private String baseCurrency;
    private String quoteCurrency;
    private Boolean trading;
    private Integer sort;
    private Integer board;
    private Integer mark;
    private String market;
    private List<String> markets;
    private BigDecimal averagePrice;
    private BigDecimal takerFeeRate;
    private BigDecimal makerFeeRate;
    private BigDecimal takerCoefficient;
    private BigDecimal makerCoefficient;
    private Boolean marginTrade;
}
