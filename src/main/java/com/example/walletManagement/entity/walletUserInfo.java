package com.example.walletManagement.entity;

import javax.persistence.Column;
import javax.persistence.Id;

public class walletUserInfo {

    @Id
    @Column(name = "phoneNumber")
    private long phoneNumber;
    @Column(name = "balance")
    private int balance;

    public walletUserInfo(long phoneNumber, int balance)
    {
        this.phoneNumber=phoneNumber;
        this.balance=balance;
    }

    public walletUserInfo(){}  // default constructor

                                                      // getters and setters
    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
