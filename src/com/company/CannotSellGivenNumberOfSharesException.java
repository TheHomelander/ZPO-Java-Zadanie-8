package com.company;

public class CannotSellGivenNumberOfSharesException extends Exception{
    private int ownedShares;
    private int valueToSell;

    CannotSellGivenNumberOfSharesException(String msg, int valueToSell)
    {
        super(msg);
        this.valueToSell = valueToSell;
    }

    public int getOwnedShares() {
        return ownedShares;
    }

    public void setOwnedShares(int ownedShares) {
        this.ownedShares = ownedShares;
    }

    public int getValueToSell() {
        return valueToSell;
    }

    public void setValueToSell(int valueToSell) {
        this.valueToSell = valueToSell;
    }
}
