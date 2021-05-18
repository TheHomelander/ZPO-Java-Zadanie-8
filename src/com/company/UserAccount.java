package com.company;

import java.util.ArrayList;
import java.util.List;

public class UserAccount {
    private String userName;
    private final String idLabel;

    private volatile List<Share> myShares = new ArrayList<>();

    private static int iD = 0;


    public UserAccount(String userName) {
        this.userName = userName;
        idLabel = "USER" + iD;
        iD = iD + 1;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdLabel() {
        return idLabel;
    }

    public List<Share> getMyShares() {
        return myShares;
    }

    public void setMyShares(List<Share> myShares) {
        this.myShares = myShares;
    }

    public static int getiD() {
        return iD;
    }

    public static void setiD(int iD) {
        UserAccount.iD = iD;
    }

    public String generateStringOfOwnedShares()
    {
        String ownedShares = "=== Your Owned shares ===\n";
        for(Share ts : myShares)ownedShares = ownedShares + '\n' + ts.toString();
        return ownedShares + "\n\n========= End  ==========\n";
    }

}
