package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StockHandler {
    private StockExchange myStock = null;
    private UserAccount myUser = null;
    private volatile List<Trade> currentTrades = new ArrayList<>();

    StockHandler(UserAccount user)
    {
        myUser = user;
    }

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

    public void updatePrices() {
        boolean isOperationAdding = ( generateRandomIntInRange(0 , 1) == 0 );
        final int minPriceChange = 1;
        final int maxPriceChange = 3;
        int priceChangeValue;
        List<Share> availableShares = myStock.getAvailableShares();

        for(Share ts : availableShares)
        {
            priceChangeValue = generateRandomIntInRange(maxPriceChange, minPriceChange);
            if(isOperationAdding)
            {
                    ts.setPricePerShare(ts.getPricePerShare() + priceChangeValue);
            }
            else
            {
                    ts.setPricePerShare(ts.getPricePerShare() + priceChangeValue);
            }
            for( Share us : myUser.getMyShares() ){
                if(ts.getName() == us.getName())
                    us.setPricePerShare(ts.getPricePerShare());
            }
        }
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
            numberOfAllExhistingShares = generateRandomIntInRange(maxNumberOfAllExistingShares, minNumberOfAllExistingShares);
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


    public void executeValidTrades()
    {
        Share shareInStock;
        Trade trade;
        for(Iterator<Trade> iterator = currentTrades.iterator(); iterator.hasNext();)
        {
            trade = iterator.next();
            shareInStock = myStock.getShareByName(trade.getStockName(), myStock.getAvailableShares());
            if(trade.getOperationType() == Trade.OperationType.Buy)
            {
                if( trade.getSharePrice() >=  shareInStock.getPricePerShare() && trade.getNumberOfShares() <= shareInStock.getNumberOfOwnedShares() )
                {
                    myStock.buyStock(shareInStock, trade.getNumberOfShares(), myUser, trade.getTradeID());
                    iterator.remove();
                }
            }
            else
            {
                if( trade.getSharePrice() <=  shareInStock.getPricePerShare() )
                {
                    myStock.sellStock(shareInStock, trade.getNumberOfShares(), myUser, trade.getTradeID());
                    iterator.remove();
                }
            }
        }
    }

    public static void main(String[] args) {
        // write your code here
        UserAccount userAccount = new UserAccount();
        StockHandler stockHandler = new StockHandler(userAccount);
        UserInteractions userInteractions = new UserInteractions(stockHandler, userAccount);
        stockHandler.initStockExchange();
        StockRunCycle sh = new StockRunCycle(stockHandler);
        sh.start();
        while (true)
        {
            System.out.println(userInteractions.generateActionList());
            int userInput = userInteractions.getIntFromUser();
            switch (userInput) {
                case 1:
                    System.out.println(stockHandler.getMyStock().generateStringOfAvailableShares());
                    break;
                case 2:
                    System.out.println("===== Shares availabe to buy =====\n" + stockHandler.getMyStock().generateStringOfAvailableShares());
                    userInteractions.makeTradeRequest();
                    break;
                case 3:
                    System.out.println(stockHandler.getMyUser().generateStringOfOwnedShares());
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        }

    }


}
