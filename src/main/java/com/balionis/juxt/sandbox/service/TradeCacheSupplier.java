package com.balionis.juxt.sandbox.service;

import com.balionis.juxt.sandbox.model.CreateTradeEvent;

import java.util.List;

public interface TradeCacheSupplier {
    List<CreateTradeEvent> readTrades();
}
