package com.company;

public class StockRunCycle extends  Thread{
    private StockHandler stockExchange;

    StockRunCycle(StockHandler stockExchange)
    {
        super("Stock processes");
        this.stockExchange = stockExchange;
    }

    public StockHandler getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(StockHandler stockExchange) {
        this.stockExchange = stockExchange;
    }

    public void run()
    {
        while (true)
        {
            try
            {

                stockExchange.updatePrices();
                stockExchange.executeValidTrades();
                Thread.sleep(5000);

            }
            catch (InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            catch (SharePriceOutOfBoundsException ex)
            {
                System.err.println("Tried to update Share: " + ex.getShare().getName() + " price with invalid value" + ex.getSharePrice());
                ex.getShare().setPricePerShare( ex.generateCorrectPriceWithinBounds(ex.getSharePrice()) );
            }
        }
    }


}
