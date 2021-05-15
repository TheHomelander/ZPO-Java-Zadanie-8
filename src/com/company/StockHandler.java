package com.company;

import java.util.List;

public class StockHandler {
    private StockExchange myStock = null;
    private UserAccount myUser = null;
    private List<Trade> currentTrades = null;


    public StockExchange getMyStock() {
        return myStock;
    }

    public void setMyStock(StockExchange myStock) {
        this.myStock = myStock;
    }

    public UserAccount getMyUser() {
        return myUser;
    }

    public void setMyUser(UserAccount myUser) {
        this.myUser = myUser;
    }

    public List<Trade> getCurrentTrades() {
        return currentTrades;
    }

    public void setCurrentTrades(List<Trade> currentTrades) {
        this.currentTrades = currentTrades;
    }

    public void initStockExchange()
    {
        myStock = new StockExchange();
        initShares();
    }


    private int generateRandomIntInRange(int max, int min)
    {
        int i = (int) (Math.random() * (max - min + 1)) + min;
        return i;
    }

    private void initShares() {
        Integer[] possibleSharesNameIndex = {0, 1 ,2 ,3 ,4 , 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        final int maxShares = 10;
        final int minShares = 5;
        final int numberOfSharesToRandomize = generateRandomIntInRange(maxShares,minShares);

        final int minNameIndex = 0;
        final int maxNameIndex = 14;

        final int minPricePerShare = 1;
        final int maxPricePerShare = 1000;

        final int maxNumberOfAllExistingShares = 50000;
        final int minNumberOfAllExistingShares = 10000;
        int numberOfAllExhistingShares;

        int randomShareNameIndex = 0;
        System.out.println("NUMBER OF SHARES " + numberOfSharesToRandomize);
        for(int i = 0 ; i < numberOfSharesToRandomize ; i++)
        {
            numberOfAllExhistingShares = generateRandomIntInRange(maxNumberOfAllExistingShares, minNumberOfAllExistingShares);;
            while ( true)
            {
                randomShareNameIndex = generateRandomIntInRange(maxNameIndex, minNameIndex);
                if( possibleSharesNameIndex[randomShareNameIndex] != null )
                {
                    myStock.getAvailableShares().add(
                            new Share(
                                    StockExchange.availableSharesArray[possibleSharesNameIndex[randomShareNameIndex]],
                                    generateRandomIntInRange(maxPricePerShare, minPricePerShare),
                                    numberOfAllExhistingShares,
                                    numberOfAllExhistingShares
                            )
                    );
                    possibleSharesNameIndex[randomShareNameIndex] = null;
                    break;
                }
            }
        }

    }

    public static void main(String[] args) {
        // write your code here

        StockHandler stockHandler = new StockHandler();
        stockHandler.initStockExchange();
        StockRunCycle sh = new StockRunCycle(stockHandler.getMyStock());
        sh.start();



    }


}
