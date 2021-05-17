package com.company;

public class InvalidTradeOperationException extends Exception {

    private int operationInput;

    InvalidTradeOperationException(String msg, int operationInput)
    {
        super(msg);
        this.operationInput = operationInput;
    }

    public int getOperationInput() {
        return operationInput;
    }

    public void setOperationInput(int operationInput) {
        this.operationInput = operationInput;
    }
}
