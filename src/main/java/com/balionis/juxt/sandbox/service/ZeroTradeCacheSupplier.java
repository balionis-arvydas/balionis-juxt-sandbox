package com.balionis.juxt.sandbox.service;

import com.balionis.juxt.sandbox.model.CreateTradeEvent;
import com.balionis.juxt.sandbox.model.TradeEvent;

import java.util.ArrayList;
import java.util.List;

public class ZeroTradeCacheSupplier implements TradeCacheSupplier {
    @Override
    public List<CreateTradeEvent> readTrades() {
        return new ArrayList<>();
    }
}
