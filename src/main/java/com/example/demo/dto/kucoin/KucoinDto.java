package com.example.demo.dto.kucoin;

import com.example.demo.dto.ExchangeResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class KucoinDto extends ExchangeResponse<KucoinCoinDto> {
    private Boolean success;
    private String code;
    private String msg;
    private Boolean retry;
    private List<KucoinCoinDto> data;

    @Override
    public List<KucoinCoinDto> getData() {
        return data;
    }
}
