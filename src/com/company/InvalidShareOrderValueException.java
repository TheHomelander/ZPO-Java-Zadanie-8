package com.company;

public class InvalidShareOrderValueException extends Exception{

    private int orderValue;
    InvalidShareOrderValueException(int orderVal , String msg)
    {
        super(msg);
        this.orderValue = orderVal;
    }

    public int getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(int orderValue) {
        this.orderValue = orderValue;
    }
}
