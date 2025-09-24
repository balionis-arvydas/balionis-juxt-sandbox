package com.balionis.juxt.sandbox.model;

import java.util.Objects;
import java.util.UUID;

public abstract class TradeEvent {
    private UUID id;

    private TradeEventType type;

    public TradeEvent(UUID id, TradeEventType type) {
        this.id = Objects.requireNonNull(id);
        this.type = Objects.requireNonNull(type);
    }

    public UUID getId() {
        return id;
    }

    public TradeEventType getType() {
        return type;
    }

}
