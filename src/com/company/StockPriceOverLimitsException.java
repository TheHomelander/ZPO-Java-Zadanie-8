package com.company;

public class StockPriceOverLimitsException extends Exception{

    StockPriceOverLimitsException(String msg)
    {
        super(msg);
    }
}
