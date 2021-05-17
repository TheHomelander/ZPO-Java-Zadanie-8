package com.company;

public class SharePriceOutOfBoundsException extends Exception{

    private double sharePrice;
    private Share share;

    SharePriceOutOfBoundsException(Share share, double price , String msg)
    {
        super(msg);
        sharePrice = price;
        this.share = share;
    }

    public double getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(double sharePrice) {
        this.sharePrice = sharePrice;
    }

    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }

    public double generateCorrectPriceWithinBounds( double priceValue )
    {
        if(priceValue < Share.minPricePerShare) return Share.minPricePerShare;
        else if(priceValue > Share.maxPricePerShare) return Share.maxPricePerShare;
        else return priceValue;
    }

}
