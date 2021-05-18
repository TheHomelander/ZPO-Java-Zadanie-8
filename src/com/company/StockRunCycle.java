package com.company;

public class StockRunCycle extends  Thread{
    private StockHandler stockExchange;

    StockRunCycle(StockHandler stockExchange)
    {
        super("Stock processes");
        this.stockExchange = stockExchange;
    }

    public void run()
    {
        try
        {
            while (true) {
                stockExchange.updatePrices();
                stockExchange.executeValidTrades();
                Thread.sleep(5000);
            }
        }catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public StockHandler getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(StockHandler stockExchange) {
        this.stockExchange = stockExchange;
    }
}
