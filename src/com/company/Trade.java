package com.company;

public class Trade {
    private String stockName;
    private OperationType operationType;
    private double numberOfShares;
    private double sharePrice;

    public enum OperationType{
        Sell, Buy
    };

    Trade(OperationType operationType, String stockName, double numberOfShares, double sharePrice)
    {
        this.operationType = operationType;
        this.stockName = stockName;
        this.numberOfShares = numberOfShares;
        this.sharePrice = sharePrice;

    }




}
