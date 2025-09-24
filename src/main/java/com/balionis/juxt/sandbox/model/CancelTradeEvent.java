package com.balionis.juxt.sandbox.model;

import java.util.UUID;

public class CancelTradeEvent extends TradeEvent {
    public CancelTradeEvent(UUID id) {
        super(id, TradeEventType.CANCEL);
    }
}
