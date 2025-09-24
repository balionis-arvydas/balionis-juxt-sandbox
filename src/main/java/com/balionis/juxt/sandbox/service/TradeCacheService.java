package com.balionis.juxt.sandbox.service;

import com.balionis.juxt.sandbox.model.CreateTradeEvent;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TradeCacheService {
    private final Map<UUID, CreateTradeEvent> tradesCache = new ConcurrentHashMap<>();

    public TradeCacheService(TradeCacheSupplier supplier) {
        // TODO: bad idea to load in the constructor, consider some kind of (lazy) init method or "PostConstructor"
        supplier.readTrades().forEach(tradeEvent -> tradesCache.put(tradeEvent.getId(), tradeEvent));
    }

    public Optional<CreateTradeEvent> findTrade(UUID id) {
        return Optional.ofNullable(tradesCache.get(id));
    }

    public void addTrade(CreateTradeEvent trade) {
        tradesCache.put(trade.getId(), trade);
    }

}
