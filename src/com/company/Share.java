package com.company;

public class Share {
    static public double minPricePerShare = 1.0;
    static public double maxPricePerShare = 10000.0;

    private int numberOfAllAvailableShares;
    private int numberOfOwnedShares;

    private String name;

    private double pricePerShare;


    Share(String name, double pricePerShare, int numberOfOwnedShares, int numberOfAllAvailableShares)
    {
        this.name = name;
        this.pricePerShare = pricePerShare;
        this.numberOfOwnedShares = numberOfOwnedShares;
        this.numberOfAllAvailableShares = numberOfAllAvailableShares;
    }

    @Override
    public String toString() {
        return "Share{" +
                "name='" + name + '\'' +
                ", pricePerShare=" + String.format("%.4f" ,pricePerShare) +
                ", Owned shares=" + numberOfOwnedShares +
                ", All Share Number=" + numberOfAllAvailableShares +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfAllAvailableShares() {
        return numberOfAllAvailableShares;
    }

    public void setNumberOfAllAvailableShares(int numberOfAllAvailableShares) {
        this.numberOfAllAvailableShares = numberOfAllAvailableShares;
    }

    public int getNumberOfOwnedShares() {
        return numberOfOwnedShares;
    }

    public void setNumberOfOwnedShares(int numberOfOwnedShares) {
        this.numberOfOwnedShares = numberOfOwnedShares;
    }

    public double getPricePerShare() {
        return pricePerShare;
    }

    public void setPricePerShare(double pricePerShare) {
        this.pricePerShare = pricePerShare;
    }
}
