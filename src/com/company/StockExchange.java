package com.company;

import java.util.ArrayList;
import java.util.List;

public class StockExchange {
    private volatile List<Share> availableShares = new ArrayList<>();
    private int numberOfShares = 0;
    public static final String[] availableSharesNameArray = {
                                                             "PKOBP", "PNORLEN", "CDPROJECT", "TESLA", "LOTOS",
                                                             "ALLEGRO", "PZU", "ACTION", "AGORA", "AIRWAY",
                                                             "ALTO", "AMICA", "ARCTIC", "GME", "BALTONA"
                                                            };



    StockExchange()
    {
    }

    public List<Share> getAvailableShares() {
        return availableShares;
    }

    public void setAvailableShares(List<Share> availableShares) {
        this.availableShares = availableShares;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public String generateStringOfAvailableShares()
    {
        String generatedString = "";
        int index = 0;
        for(Share ts : availableShares){
            generatedString += "["+ index + "]" + ts.toString() + "\n";
            index = index + 1;
        }
        return generatedString;
    }

    public Share getShareByName(String stockName, List<Share> sc)
    {
        for(Share ts : sc)
        {
            if( ts.getName().equalsIgnoreCase(stockName))
            {
                return ts;
            }
        }
        return  null;
    }

    public void buyStock(Share shareInStock, int numberOfShares, UserAccount myUser) throws SharePriceOutOfBoundsException
    {
        double newPrice;
        Share userShare;

        shareInStock.setNumberOfOwnedShares(shareInStock.getNumberOfOwnedShares() - numberOfShares);
        newPrice = shareInStock.getPricePerShare() + ((double)(numberOfShares) / (shareInStock.getNumberOfAllAvailableShares()) * shareInStock.getPricePerShare());

        if(newPrice > Share.maxPricePerShare)
            throw new SharePriceOutOfBoundsException(shareInStock, newPrice, "Price too big while selling");

        shareInStock.setPricePerShare(newPrice);

        if( (userShare = getShareByName(shareInStock.getName(), myUser.getMyShares())) == null )
        {
            userShare = new Share(shareInStock.getName(),shareInStock.getPricePerShare(), numberOfShares, shareInStock.getNumberOfAllAvailableShares() );
            myUser.getMyShares().add(userShare);
        }
        else
            userShare.setPricePerShare(newPrice);


    }

    public void sellStock(Share shareInStock, int numberOfShares, UserAccount myUser) throws SharePriceOutOfBoundsException
    {
        Share userShare = getShareByName(shareInStock.getName(), myUser.getMyShares());

        double newPrice = shareInStock.getPricePerShare() - ((double)(numberOfShares) / (shareInStock.getNumberOfAllAvailableShares()) * shareInStock.getPricePerShare());

        if(newPrice < Share.minPricePerShare)
            throw new SharePriceOutOfBoundsException(shareInStock, newPrice, "Updated price per share is too small");

        userShare.setNumberOfOwnedShares(userShare.getNumberOfOwnedShares() - numberOfShares);
        userShare.setPricePerShare(newPrice);

        shareInStock.setNumberOfOwnedShares(shareInStock.getNumberOfOwnedShares() + numberOfShares);
        shareInStock.setPricePerShare(newPrice);


    }



}
