package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StockHandler {
    private StockExchange myStock = null;
    private UserAccount myUser;
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

    private int generateRandomIntInRange(int max, int min)
    {
        int i = (int) (Math.random() * (max - min + 1)) + min;
        return i;
    }

    public void updatePrices() throws SharePriceOutOfBoundsException
    {
        boolean isOperationAdding;
        final int operationAddition = 0;
        final int operationSubtraction = 1;

        final int minPriceChange = 1;
        final int maxPriceChange = 3;
        int priceChangeValue;

        double newPrice;
        List<Share> availableShares = myStock.getAvailableShares();

        for(Share ts : availableShares)
        {
                isOperationAdding = ( generateRandomIntInRange(operationAddition , operationSubtraction) == operationAddition );
                priceChangeValue = generateRandomIntInRange(maxPriceChange, minPriceChange);
                if (isOperationAdding) {
                    newPrice = ts.getPricePerShare() + priceChangeValue;
                    if (newPrice > Share.maxPricePerShare)
                        throw new SharePriceOutOfBoundsException( ts, newPrice, "Updated Price Value is out of bounds" );
                    ts.setPricePerShare(newPrice);
                } else {
                    newPrice = ts.getPricePerShare() + priceChangeValue;
                    if (newPrice > Share.maxPricePerShare)
                        throw new SharePriceOutOfBoundsException( ts, newPrice, "Updated Price Value is out of bounds" );
                    ts.setPricePerShare(newPrice);
                }
            for( Share us : myUser.getMyShares() )
            {
                if( ts.getName().compareTo( us.getName()) == 0 )
                {
                    us.setPricePerShare(ts.getPricePerShare());
                }
            }
        }
    }


    public void initStockExchange() {
        myStock = new StockExchange();
        Integer[] possibleSharesNameIndex = {0, 1 ,2 ,3 ,4 , 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        final int maxShares = 10;
        final int minShares = 5;
        final int numberOfSharesToRandomize = generateRandomIntInRange(maxShares,minShares);
        myStock.setNumberOfShares(numberOfSharesToRandomize);

        final int minNameIndex = 0;
        final int maxNameIndex = 14;

        final int minPricePerShare = 1;
        final int maxPricePerShare = 1000;

        final int maxNumberOfAllExistingShares = 50000;
        final int minNumberOfAllExistingShares = 10000;
        int numberOfAllExhistingShares;
        int randomShareNameIndex ;

        for(int i = 0 ; i < numberOfSharesToRandomize ; i++)
        {
            numberOfAllExhistingShares = generateRandomIntInRange(maxNumberOfAllExistingShares, minNumberOfAllExistingShares);
            while ( true )
            {
                randomShareNameIndex = generateRandomIntInRange(maxNameIndex, minNameIndex);
                if( possibleSharesNameIndex[randomShareNameIndex] != null )
                {
                    myStock.getAvailableShares().add(
                            new Share(
                                        StockExchange.availableSharesNameArray[possibleSharesNameIndex[randomShareNameIndex]],
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

    public String generateTradeCompletedMessage(Trade.OperationType ot, String name, String tradeID, String userID)
    {
        return  "*****************************************************\n" +
                "Trade Completed:\n" +
                "Trade operation: " + ot + '\n' +
                "Share name: " + name + '\n' +
                "Trade iD: " + tradeID + "\n" +
                "User iD: " + userID + '\n' +
                "*****************************************************\n" ;
    }

    public void executeValidTrades() throws SharePriceOutOfBoundsException
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
                    myStock.buyStock(shareInStock, trade.getNumberOfShares(), myUser);
                    System.out.println(
                                        generateTradeCompletedMessage(
                                                                        trade.getOperationType(),
                                                                        shareInStock.getName(),
                                                                        trade.getTradeID(),
                                                                        myUser.getIdLabel()
                                                                     )
                                      );
                    iterator.remove();
                }
            }
            else
            {
                if( trade.getSharePrice() <=  shareInStock.getPricePerShare() )
                {
                    myStock.sellStock(shareInStock, trade.getNumberOfShares(), myUser);
                    System.out.println(
                            generateTradeCompletedMessage(
                                    trade.getOperationType(),
                                    shareInStock.getName(),
                                    trade.getTradeID(),
                                    myUser.getIdLabel()
                            )
                    );
                    iterator.remove();
                }
            }
        }
    }

    public static void main(String[] args) {

        UserAccount userAccount = new UserAccount("Admin");
        StockHandler stockHandler = new StockHandler(userAccount);
        UserInteractions userInteractions = new UserInteractions(stockHandler, userAccount);

        stockHandler.initStockExchange();
        StockRunCycle sh = new StockRunCycle(stockHandler);
        sh.start();

        while (true)
        {
            try {
                System.out.println(userInteractions.generateUserAvailableActionsLegend());
                int userInput = userInteractions.getIntFromUser();
                switch (userInput) {
                    case 1:
                        System.out.println(stockHandler.getMyStock().generateStringOfAvailableShares());
                        break;
                    case 2:
                        System.out.println(
                                            "===== Current Stock Prices =====\n" +
                                            stockHandler.getMyStock().generateStringOfAvailableShares()
                                          );
                        System.out.println(stockHandler.getMyUser().generateStringOfOwnedShares());
                        userInteractions.makeTradeRequest();
                        break;
                    case 3:
                        System.out.println(stockHandler.getMyUser().generateStringOfOwnedShares());
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Selected option doesn't exist");
                }
            }
            catch (InvalidShareOrderValueException ex)
            {
                System.out.println("Invalid number of shares in your order");
                ex.printStackTrace(System.err);
            }
            catch (ShareNotExistingException ex)
            {
                System.out.println("Share with given index does not exist");
                ex.printStackTrace(System.err);
            }
            catch (CannotSellGivenNumberOfSharesException ex)
            {
                if(ex.getValueToSell() <= 0 )
                    System.out.println("Invalid number of Shares: " + ex.getValueToSell() + '\n');
                else
                    System.out.println("Cannot sell shares you don't own\n" + ex.getMessage());
                ex.printStackTrace(System.err);
            }
            catch (InvalidTradeOperationException ex)
            {
                System.out.println(ex.getMessage() + " your input: " + ex.getOperationInput());
                ex.printStackTrace(System.err);
            }
            catch (SharePriceOutOfBoundsException ex)
            {
                System.out.println("Share price out of bounds" + ex.getMessage());
                ex.printStackTrace(System.err);
            }
        }

    }


}
