package com.balionis.juxt.sandbox.service;

import com.balionis.juxt.sandbox.model.CurrencyType;

import java.math.BigDecimal;
import java.util.Map;

public interface TradePositionSupplier {
    Map<CurrencyType, BigDecimal> readPositions();
}
