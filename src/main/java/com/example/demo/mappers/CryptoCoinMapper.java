package com.example.demo.mappers;

import com.example.demo.dto.CoinDto;
import com.example.demo.model.CryptoCoin;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingConstants;

@MapperConfig(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CryptoCoinMapper<dto extends CoinDto, model extends CryptoCoin> {

    model convertToModel(dto coinDto);
}
