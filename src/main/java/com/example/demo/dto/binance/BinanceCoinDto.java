package com.example.demo.dto.binance;

import com.example.demo.dto.CoinDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BinanceCoinDto extends CoinDto {

    private BigInteger id;
    private BigDecimal issuePrice;
    private BigDecimal issuePriceUsed;
    private LocalDateTime issueDate;
    private String source;
    private String name;
    private String fullName;
    private String localFullName;
    private Integer cmcUniqueId;
    private String logo;
    private String symbol;
    private BigInteger circulatingSupply;
    private BigInteger maxSupply;
    private BigInteger totalSupply;
    private BigDecimal volume;
    private BigDecimal volumeGlobal;
    private Integer rank;
    private BigDecimal dayChange;
    private BigInteger dayChangeAmount;
    private BigDecimal marketCap;
    private BigDecimal price;
    private String mapperName;
    private List<String> tags;
    private List<TagInfo> tagInfos;
    private String url;
    private String imageUrl;
    private List<String> explorerUrls;
    private String website;
    private String tradeUrl;
    private String slug;
    private String baseAsset;
    private String quoteAsset;
    private Integer hidden;
    private Boolean specialAsset;
    private Integer reverse;
    private BigInteger listingTime;
    private Integer highLight;
    private Integer legalMoney;
    private BigDecimal marketCapDominance;
    private BigDecimal allTimeHighPrice;
    private LocalDateTime allTimeHighDate;
    private String allTimeHighSource;
    private BigDecimal allTimeLowPrice;
    private LocalDateTime allTimeLowDate;
    private String allTimeLowSource;
    private BigInteger listingCountdown;
    private BigDecimal fullyDilutedMarketCap;
}
