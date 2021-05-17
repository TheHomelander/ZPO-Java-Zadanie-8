package com.company;

public class ShareNotExistingException extends Exception{

    private int selectShareIndex;

    ShareNotExistingException(int shareIndex, String msg)
    {
        super(msg);
        selectShareIndex = shareIndex;
    }

    public int getSelectShareIndex() {
        return selectShareIndex;
    }

    public void setSelectShareIndex(int selectShareIndex) {
        this.selectShareIndex = selectShareIndex;
    }
}
