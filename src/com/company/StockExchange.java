package com.company;

import java.util.ArrayList;
import java.util.List;

public class StockExchange {
    private List<Share> availableShares = new ArrayList<>();
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
        for(Share ts : availableShares){
            generatedString += ts.toString() + "\n";
        }
        return generatedString;
    }

    private int generateRandomIntInRange(int max, int min)
    {
        int i = (int) (Math.random() * (max - min + 1)) + min;
        return i;
    }

/*    private double returnRandomDoubleInRange(double max, double min)
    {
        double i = (Math.random() * (max - min + 1)) + min;
        long factor = (long) Math.pow(10, 2);
        i = i * factor;
        long tmp = Math.round(i);
        return (double) tmp / factor;
    }*/

    public void updatePrices() {
        boolean isOperationAdding = ( generateRandomIntInRange(0 , 1) == 0 );
        final int minPriceChange = 1;
        final int maxPriceChange = 3;
        int priceChangeValue;

        for(Share ts : availableShares)
        {
            priceChangeValue = generateRandomIntInRange(maxPriceChange, minPriceChange);
            if(isOperationAdding)
            {
                if((ts.getPricePerShare() + priceChangeValue) <= 1000)
                {
                    ts.setPricePerShare(ts.getPricePerShare() + priceChangeValue);
                }else
                {
                    ts.setPricePerShare(1000);
                }
            }
            else
            {
                if((ts.getPricePerShare() - priceChangeValue) >= 1)
                {
                    ts.setPricePerShare(ts.getPricePerShare() + priceChangeValue);
                }else
                {
                    ts.setPricePerShare(1);
                }
            }
        }
    }

    public void buyStock(String name, int numberOfShares)
    {

    }

    public void sellStock(String name, int numberOfShares)
    {

    }





}
