package com.company;

import java.util.List;

public class UserAccount {
    private String userName;
    private final String idLabel;

    private List<Share> myShares;
    private List<Trade> myTrades;

    private static int iD = 0;


    public UserAccount() {
        idLabel = "USER" + iD;
        iD = iD + 1;
    }
}
