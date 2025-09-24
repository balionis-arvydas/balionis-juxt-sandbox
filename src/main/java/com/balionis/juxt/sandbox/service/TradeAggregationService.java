package com.balionis.juxt.sandbox.service;

import com.balionis.juxt.sandbox.model.CancelTradeEvent;
import com.balionis.juxt.sandbox.model.CreateTradeEvent;
import com.balionis.juxt.sandbox.model.Currency;
import com.balionis.juxt.sandbox.model.CurrencyType;
import com.balionis.juxt.sandbox.model.TradeEvent;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class TradeAggregationService {

    private final TradeCacheService tradeCacheService;
    private final Map<CurrencyType, BigDecimal> positions = new ConcurrentHashMap<>();

    public TradeAggregationService(TradeCacheService tradeCacheService, TradePositionSupplier positionSupplier) {
        this.tradeCacheService = tradeCacheService;
        // TODO: bad idea to load in the constructor, consider some kind of (lazy) init method or "PostConstructor"
        positionSupplier.readPositions().forEach((k,v) -> positions.put(k,v));
    }

    public void addTrade(TradeEvent event) {
        switch (event.getType()) {
            case CREATE: processCreateTradeEvent((CreateTradeEvent) event);
                break;
            case CANCEL: processCancelTradeEvent((CancelTradeEvent) event);
                break;
        }
    }

    public Map<CurrencyType, BigDecimal> getPositions() {
        return Collections.unmodifiableMap(positions);
    }

    private void processCreateTradeEvent(CreateTradeEvent event) {
        tradeCacheService.addTrade(event);
        processCurrencyAmount(event.getSoldCurrency(), -1);
        processCurrencyAmount(event.getBoughtCurrency(), 1);
    }

    private void processCancelTradeEvent(CancelTradeEvent event) {
        tradeCacheService.findTrade(event.getId())
                .map(CreateTradeEvent.class::cast)
                .ifPresent(createTradeEvent -> {
                    processCurrencyAmount(createTradeEvent.getSoldCurrency(), 1);
                    processCurrencyAmount(createTradeEvent.getBoughtCurrency(), -1);
        });
    }

    private void processCurrencyAmount(Currency currency, int sign) {
        positions.computeIfPresent(currency.type(),
                (k,v) -> v.add(currency.amount().multiply(BigDecimal.valueOf(sign))));
    }
}
