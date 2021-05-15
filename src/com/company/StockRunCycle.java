package com.company;

import jdk.jfr.StackTrace;

public class StockRunCycle extends  Thread{
    private StockExchange stockExchange;

    StockRunCycle(StockExchange stockExchange)
    {
        super("Stock processes");
        this.stockExchange = stockExchange;
    }

    public void run()
    {
        try
        {
            while (true) {
                System.out.println(stockExchange.generateStringOfAvailableShares());
                stockExchange.updatePrices();
                Thread.sleep(5000);
            }
        }catch (InterruptedException ex)
        {
            System.out.println("Interrupt Exception");
        }
    }


}
