package com.balionis.juxt.sandbox.model;

import java.util.UUID;

public final class CancelTradeEvent extends TradeEvent {
    public CancelTradeEvent(UUID id) {
        super(id, TradeEventType.CANCEL);
    }
}
