package com.company;

public class Trade {
    private String stockName;
    private String userID;
    private final String tradeID;

    private OperationType operationType;
    private int numberOfShares;
    private static int iD = 0;
    private double sharePrice;

    public enum OperationType{
        Sell, Buy
    };

    Trade(OperationType operationType, String stockName, String userID, int numberOfShares, double sharePrice)
    {
        this.operationType = operationType;
        this.stockName = stockName;
        this.userID = userID;
        this.numberOfShares = numberOfShares;
        this.sharePrice = sharePrice;

        tradeID = "TRADE" + iD;
        iD = iD + 1;

    }


    @Override
    public String toString() {
        return "Trade{" +
                "stockName='" + stockName + '\'' +
                ", userID='" + userID + '\'' +
                ", operationType=" + operationType +
                ", numberOfShares=" + numberOfShares +
                ", sharePrice=" + sharePrice +
                '}';
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public double getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(double sharePrice) {
        this.sharePrice = sharePrice;
    }

    public String getTradeID() {
        return tradeID;
    }
}
