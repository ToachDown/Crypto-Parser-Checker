package com.example.demo.dto.binance;

import com.example.demo.dto.ExchangeResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BinanceDto extends ExchangeResponse<BinanceCoinDto> {
    private String code;
    private String message;
    private String messageDetails;
    private List<BinanceCoinDto> data;

    @Override
    public List<BinanceCoinDto> getData() {
        return data;
    }
}
