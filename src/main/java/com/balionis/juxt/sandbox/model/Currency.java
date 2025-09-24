package com.balionis.juxt.sandbox.model;

import java.math.BigDecimal;

public record Currency(CurrencyType type, BigDecimal amount) {
}
