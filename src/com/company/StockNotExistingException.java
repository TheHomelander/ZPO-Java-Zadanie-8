package com.company;

public class StockNotExistingException extends Exception{

    StockNotExistingException(String msg)
    {
        super(msg);
    }

}
