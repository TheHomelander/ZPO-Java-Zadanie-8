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

    private int generateRandomIntInRange(int max, int min)
    {
        int i = (int) (Math.random() * (max - min + 1)) + min;
        return i;
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
/*    private double returnRandomDoubleInRange(double max, double min)
    {
        double i = (Math.random() * (max - min + 1)) + min;
        long factor = (long) Math.pow(10, 2);
        i = i * factor;
        long tmp = Math.round(i);
        return (double) tmp / factor;
    }*/



    public void buyStock(Share shareInStock, int numberOfShares, UserAccount myUser, String tradeID)
    {
        //TODO: make exception not to set negative number of shares
        shareInStock.setNumberOfOwnedShares(shareInStock.getNumberOfOwnedShares() - numberOfShares);
        double newPrice = shareInStock.getPricePerShare() + ((double)(numberOfShares) / (shareInStock.getNumberOfAllAvailableShares()) * shareInStock.getPricePerShare());
        shareInStock.setPricePerShare(newPrice);
        Share userShare;
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
        System.out.println(
                            "*****************************************************\n" +
                            "Trade Completed:\n" +
                            "Trade operation: Buy\n" +
                            "Share name: " + shareInStock.getName() + '\n' +
                            "Trade iD: " + tradeID + "\n" +
                            "User iD: " + myUser.getIdLabel() + '\n' +
                            "*****************************************************\n"
                          );
    }

    public void sellStock(Share shareInStock, int numberOfShares, UserAccount myUser, String tradeID)
    {
        Share userShare = getShareByName(shareInStock.getName(), myUser.getMyShares());

        double newPrice = shareInStock.getPricePerShare() - ((double)(numberOfShares) / (shareInStock.getNumberOfAllAvailableShares()) * shareInStock.getPricePerShare());

        userShare.setNumberOfOwnedShares(userShare.getNumberOfOwnedShares() - numberOfShares);
        userShare.setPricePerShare(newPrice);

        shareInStock.setNumberOfOwnedShares(shareInStock.getNumberOfOwnedShares() + numberOfShares);
        shareInStock.setPricePerShare(newPrice);

        System.out.println(
                "Trade Complete:\n" +
                        "Trade operation: Sell\n" +
                        "Trade iD: " + tradeID + "\n"
        );
    }



}
