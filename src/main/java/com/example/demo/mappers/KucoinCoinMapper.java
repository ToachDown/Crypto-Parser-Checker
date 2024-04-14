package com.example.demo.mappers;

import com.example.demo.dto.kucoin.KucoinCoinDto;
import com.example.demo.model.CryptoCoin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

@Mapper(config = CryptoCoinMapper.class)
public interface KucoinCoinMapper extends CryptoCoinMapper<KucoinCoinDto, CryptoCoin> {

    @Override
    @Mapping(source = "baseCurrency", target = "name")
    @Mapping(source = "buy", target = "buyPrice")
    @Mapping(source = "sell", target = "sellPrice")
    @Mapping(source = "vol", target = "dayVolume")
    @Mapping(source = "quoteCurrency", target = "sellCoin")
    @Mapping(source = "changeRate", target = "dayChange")
    CryptoCoin convertToModel(KucoinCoinDto kucoinCoinDto);
}
