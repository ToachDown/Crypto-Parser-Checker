package com.example.demo.mappers;

import com.example.demo.dto.binance.BinanceCoinDto;
import com.example.demo.model.CryptoCoin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

@Mapper(config = CryptoCoinMapper.class)
public interface BinanceCoinMapper extends CryptoCoinMapper<BinanceCoinDto, CryptoCoin>{

    @Override
    @Mapping(source = "price", target = "buyPrice")
    @Mapping(source = "price", target = "sellPrice")
    @Mapping(source = "volume", target = "dayVolume")
    @Mapping(source = "quoteAsset", target = "sellCoin")
    @Mapping(source = "marketCap", target = "marketCup")
    CryptoCoin convertToModel(BinanceCoinDto binanceCoinDto);
}
