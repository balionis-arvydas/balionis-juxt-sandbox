package com.balionis.juxt.sandbox.model;

import java.util.Objects;
import java.util.UUID;

public class CreateTradeEvent extends TradeEvent {
    private final Currency boughtCurrency;
    private final Currency soldCurrency;
    public CreateTradeEvent(UUID id, Currency boughtCurrency, Currency soldCurrency) {
        super(id, TradeEventType.CREATE);
        this.boughtCurrency = Objects.requireNonNull(boughtCurrency);
        this.soldCurrency = Objects.requireNonNull(soldCurrency);
    }

    public Currency getBoughtCurrency() {
        return boughtCurrency;
    }

    public Currency getSoldCurrency() {
        return soldCurrency;
    }

}
