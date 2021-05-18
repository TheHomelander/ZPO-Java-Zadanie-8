package com.company;

import java.util.Scanner;

public class UserInteractions {
    UserAccount userAccount;
    StockHandler stockHandler;

    UserInteractions(StockHandler stockHandler, UserAccount currentUser)
    {
        this.stockHandler = stockHandler;
        userAccount = currentUser;
    }

    public String generateUserAvailableActionsLegend()
    {
        return "*****************************************************\n" +
                "Input 1, to see current Stock prices (PLN) with its indexes\n" +
                "Input 2, to make trade request\n" +
                "Input 3, to see your owned shares\n" +
                "Input 4, to close the stock exchange\n" +
                "*****************************************************\n";
    }

    public int getIntFromUser()
    {
        Scanner ms = new Scanner(System.in);
        return ms.nextInt();
    }

    public double getDoubleFromUser()
    {
        Scanner ms = new Scanner(System.in);
        return ms.nextDouble();
    }

    public void makeTradeRequest() throws ShareNotExistingException,
                                          InvalidShareOrderValueException,
                                          CannotSellGivenNumberOfSharesException,
                                          InvalidTradeOperationException
    {
        final int shareIndex;
        final int operationType;
        final int buy = 0;
        final int sell = 1;
        final int numberOfShares;
        final double thresholdPricePerShare;
        int maxBuyNumberOfShares;
        Share shareFromStockExchange;
        String stockName;

        System.out.println("Input index of Share ( it is the number inside square braces in the share list ):\n");
        shareIndex = getIntFromUser();

        if(shareIndex > stockHandler.getMyStock().getNumberOfShares() - 1 )
            throw new ShareNotExistingException(shareIndex, "Invalid share index number");

        System.out.println("Input operation type: 0 - Buy, 1 - Sell\n");
        operationType = getIntFromUser();

        if( operationType != buy && operationType != sell)
            throw new InvalidTradeOperationException("Invalid trade operation identifier", operationType);

        System.out.println("Input number of shares:\n");
        numberOfShares = getIntFromUser();

        System.out.println("Input price per share threshold:");
        thresholdPricePerShare = getDoubleFromUser();

        stockName = stockHandler.getMyStock().getAvailableShares().get(shareIndex).getName();
        shareFromStockExchange = stockHandler.getMyStock().getShareByName(stockName,stockHandler.getMyStock().getAvailableShares());
        maxBuyNumberOfShares = shareFromStockExchange.getNumberOfOwnedShares();

        if(operationType == buy && numberOfShares > maxBuyNumberOfShares)
            throw new InvalidShareOrderValueException(numberOfShares, "Invalid number of shares in order");

        if(operationType == sell)
            checkIfCanSell(stockName, numberOfShares);

        stockHandler.getCurrentTrades().add(
                                            new Trade(
                                                        (operationType == buy) ? Trade.OperationType.Buy : Trade.OperationType.Sell ,
                                                        stockName,
                                                        userAccount.getIdLabel(),
                                                        numberOfShares,
                                                        thresholdPricePerShare
                                                     )
                                            );
        stockHandler.executeValidTrades();
    }

    private void checkIfCanSell(String name, int valueOfSharesToSell) throws CannotSellGivenNumberOfSharesException
    {
        Share ts = stockHandler.getMyStock().getShareByName(name,userAccount.getMyShares());
        if(ts == null || ts.getNumberOfOwnedShares() < valueOfSharesToSell || valueOfSharesToSell < 1)
            throw new CannotSellGivenNumberOfSharesException("Cannot sell " + name, valueOfSharesToSell);
    }
}
