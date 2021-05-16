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
                //System.out.println(stockExchange.getMyStock().generateStringOfAvailableShares());
                stockExchange.updatePrices();
                stockExchange.executeValidTrades();
                Thread.sleep(5000);
            }
        }catch (InterruptedException ex)
        {
            System.out.println("Interrupt Exception");
        }
    }


}
