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

    public String generateActionList()
    {
        return "*****************************************************\n" +
                "Input 1, to see current Stock prices with its indexes\n" +
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

    public void makeTradeRequest()
    {
        final int shareIndex;
        final int operationType;
        final int buy = 0;
        final int numberOfShares;
        final double thresholdPricePerShare;
        String stockName;

        System.out.println("Input index of Share ( it is the number inside square braces in the share list ):\n");
        shareIndex = getIntFromUser();
        System.out.println("Input operation type: 0 - Buy, 1 - Sell\n");
        operationType = getIntFromUser();
        System.out.println("Input number of shares:\n");
        numberOfShares = getIntFromUser();
        System.out.println("Input price per share threshold:");
        thresholdPricePerShare = getDoubleFromUser();
        stockName = stockHandler.getMyStock().getAvailableShares().get(shareIndex).getName();
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
}
