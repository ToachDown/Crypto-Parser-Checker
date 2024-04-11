package com.example.demo.dto.kucoin;

import java.util.List;

public record KucoinDto(
    Boolean success,
    String code,
    String msg,
    Boolean retry,
    List<KucoinCoinDto> data
) {
}
