package com.example.transactionManagement.entity;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class transaction {

    @Id
    @GeneratedValue
    @Column(name = "transactionid")
    private int transactionID;
    @Column(name = "senderphone")
    private long senderPhone;
    @Column(name = "receiverphone")
    private long receiverPhone;
    @Column(name = "amount")
    private int amount;

    public transaction(){}

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public long getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(long senderPhone) {
        this.senderPhone = senderPhone;
    }

    public long getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(long receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
