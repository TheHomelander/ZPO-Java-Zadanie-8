package com.company;

import java.util.ArrayList;
import java.util.List;

public class StockExchange {
    private volatile List<Share> availableShares = new ArrayList<>();
    private int numberOfShares = 0;
    public static final String[] availableSharesArray = {
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
            index++;
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

    public void buyStock(Share shareInStock, int numberOfShares, UserAccount myUser)
    {
        double newPrice;
        Share userShare;
        if((shareInStock.getNumberOfOwnedShares() - numberOfShares < 0 ))numberOfShares = shareInStock.getNumberOfOwnedShares();

        shareInStock.setNumberOfOwnedShares(shareInStock.getNumberOfOwnedShares() - numberOfShares);
        newPrice = shareInStock.getPricePerShare() + ((double)(numberOfShares) / (shareInStock.getNumberOfAllAvailableShares()) * shareInStock.getPricePerShare());
        shareInStock.setPricePerShare(newPrice);

        if( (userShare = getShareByName(shareInStock.getName(), myUser.getMyShares())) == null )
        {
            userShare = new Share(shareInStock.getName(),shareInStock.getPricePerShare(), numberOfShares, shareInStock.getNumberOfAllAvailableShares() );
            myUser.getMyShares().add(userShare);
        }
        else
        {
            userShare.setPricePerShare(newPrice);
            userShare.setPricePerShare(newPrice);
        }

    }

    public void sellStock(Share shareInStock, int numberOfShares, UserAccount myUser)
    {
        Share userShare = getShareByName(shareInStock.getName(), myUser.getMyShares());

        double newPrice = shareInStock.getPricePerShare() - ((double)(numberOfShares) / (shareInStock.getNumberOfAllAvailableShares()) * shareInStock.getPricePerShare());

        userShare.setNumberOfOwnedShares(userShare.getNumberOfOwnedShares() - numberOfShares);
        userShare.setPricePerShare(newPrice);

        shareInStock.setNumberOfOwnedShares(shareInStock.getNumberOfOwnedShares() + numberOfShares);
        shareInStock.setPricePerShare(newPrice);


    }



}
