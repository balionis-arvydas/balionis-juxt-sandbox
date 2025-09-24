package com.balionis.juxt.sandbox.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.balionis.juxt.sandbox.model.CreateTradeEvent;
import com.balionis.juxt.sandbox.model.Currency;
import com.balionis.juxt.sandbox.model.CurrencyType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 */
@ExtendWith(MockitoExtension.class)
public class TradeCacheServiceTest {

    @Test
    public void testFindDefault() {

        var supplier = mock(TradeCacheSupplier.class);
        var tradeId = UUID.randomUUID();
        var trades = List.of(
                new CreateTradeEvent(UUID.randomUUID(),
                    new Currency(CurrencyType.USD, BigDecimal.valueOf(100.0)),
                    new Currency(CurrencyType.GBP, BigDecimal.valueOf(85.0))),
                new CreateTradeEvent(tradeId,
                    new Currency(CurrencyType.EUR, BigDecimal.valueOf(100.0)),
                    new Currency(CurrencyType.USD, BigDecimal.valueOf(101.0)))
                );
        when(supplier.readTrades()).thenReturn(trades);

        var service = new TradeCacheService(supplier);

        var actual = service.findTrade(tradeId);

        assertEquals(tradeId, actual.get().getId());
    }

    @Test
    public void testAddOne() {

        var supplier = mock(TradeCacheSupplier.class);
        var trades = List.of(
                new CreateTradeEvent(UUID.randomUUID(),
                        new Currency(CurrencyType.USD, BigDecimal.valueOf(100.0)),
                        new Currency(CurrencyType.GBP, BigDecimal.valueOf(85.0))),
                new CreateTradeEvent(UUID.randomUUID(),
                        new Currency(CurrencyType.EUR, BigDecimal.valueOf(100.0)),
                        new Currency(CurrencyType.USD, BigDecimal.valueOf(101.0)))
        );
        when(supplier.readTrades()).thenReturn(trades);

        var service = new TradeCacheService(supplier);

        var tradeId = UUID.randomUUID();
        var trade = new CreateTradeEvent(tradeId,
                new Currency(CurrencyType.GBP, BigDecimal.valueOf(90.0)),
                new Currency(CurrencyType.EUR, BigDecimal.valueOf(100.0)));

        service.addTrade(trade);
        var actual = service.findTrade(tradeId);

        assertEquals(tradeId, actual.get().getId());
    }

    @Test
    public void testFindNone() {

        var supplier = mock(TradeCacheSupplier.class);
        when(supplier.readTrades()).thenReturn(List.of());

        var service = new TradeCacheService(supplier);

        var tradeId = UUID.randomUUID();
        var actual = service.findTrade(tradeId);

        assertFalse(actual.isPresent());
    }
}
