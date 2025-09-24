# balionis-juxt-sandbox

## Requirements 

Background
At our bank we allow traders to book foreign exchange trades. A trade has:

An unique id

Some currency being bought

Some currency being sold

After a trade is created it may later be cancelled.

Our trading system must produce a stream of events representing trading activity.

Our trading system will also provide some aggregated information about the trades in the system.

Part 1
Create a data model for events that will represent trading activity.

CurrencyType
    EUR,USD,GBP;

Currency
    CurrencyType type;
    BidDecimal amount;

Trade
    UUID id

CreateTradeEvent : Trade 
    Currency soldCurrency;
    Currency boughtCurrency;

CancelTradeEvent : Trade

Part 2
Create some code to aggregate a given collection of trade events and produce the net balance for each currency 
(bought currency increases the balance, sold currency reduces the balance).

During this exercise, think carefully about the interface to your code, and appropriate testing.

We’re here to answer questions about the requirements and clarify where things are not clear.

TradeCache
    Map<UUID, Trade> trades; 

TradeAggregationService
    TradeCache tradeCache;
    Map<CurrencyType, BigDecimal> positions;        // concurrency? 

    addTrade (CreateTradeEvent | CancelTradeEvent)
    Map<CurrencyType, BigDecimal> getPositions()    // concurrency?
    
    
    







