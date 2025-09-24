package com.balionis.juxt.sandbox.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.balionis.juxt.sandbox.model.CancelTradeEvent;
import com.balionis.juxt.sandbox.model.CreateTradeEvent;
import com.balionis.juxt.sandbox.model.Currency;
import com.balionis.juxt.sandbox.model.CurrencyType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

/**
 *
 */
@ExtendWith(MockitoExtension.class)
public class TradeAggregationServiceTest {
    @Test
    public void testDefaults() {

        var cacheService = mock(TradeCacheService.class);

        var service = new TradeAggregationService(cacheService, new ZeroTradePositionSupplier());

        var positions = service.getPositions();
        assertEquals(CurrencyType.values().length, positions.size());
        assertEquals(BigDecimal.ZERO, positions.get(CurrencyType.EUR));
    }

    @Test
    public void testAddOne() {

        var trade = new CreateTradeEvent(UUID.randomUUID(),
                new com.balionis.juxt.sandbox.model.Currency(CurrencyType.GBP, BigDecimal.valueOf(90.0)),
                new Currency(CurrencyType.EUR, BigDecimal.valueOf(100.0)));

        var cacheService = mock(TradeCacheService.class);
        doNothing().when(cacheService).addTrade(trade);

        var service = new TradeAggregationService(cacheService, new ZeroTradePositionSupplier());

        service.addTrade(trade);

        var positions = service.getPositions();
        assertEquals(CurrencyType.values().length, positions.size());
        assertEquals(BigDecimal.valueOf(-100.0), positions.get(CurrencyType.EUR));
        assertEquals(BigDecimal.valueOf(90.0), positions.get(CurrencyType.GBP));
    }

    @Test
    public void testCancelOne() {

        var tradeId = UUID.randomUUID();
        var createTrade = new CreateTradeEvent(tradeId,
                new com.balionis.juxt.sandbox.model.Currency(CurrencyType.GBP, BigDecimal.valueOf(90.0).setScale(2)),
                new Currency(CurrencyType.EUR, BigDecimal.valueOf(100.0).setScale(2)));

        var cacheService = mock(TradeCacheService.class);
        doNothing().when(cacheService).addTrade(createTrade);
        when(cacheService.findTrade(tradeId)).thenReturn(Optional.of(createTrade));

        var service = new TradeAggregationService(cacheService, new ZeroTradePositionSupplier());

        service.addTrade(createTrade);

        var cancelTrade = new CancelTradeEvent(tradeId);
        service.addTrade(cancelTrade);

        var positions = service.getPositions();
        assertEquals(CurrencyType.values().length, positions.size());
        assertEquals(BigDecimal.ZERO.setScale(2), positions.get(CurrencyType.EUR));
        assertEquals(BigDecimal.ZERO.setScale(2), positions.get(CurrencyType.GBP));
    }
}
