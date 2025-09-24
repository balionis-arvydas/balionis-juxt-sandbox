package com.balionis.juxt.sandbox.service;

import com.balionis.juxt.sandbox.model.CurrencyType;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ZeroTradePositionSupplier implements TradePositionSupplier {
    @Override
    public Map<CurrencyType, BigDecimal> readPositions() {
        return Arrays.stream(CurrencyType.values())
                .collect(Collectors.toMap(Function.identity(), e -> BigDecimal.ZERO));
    }
}
