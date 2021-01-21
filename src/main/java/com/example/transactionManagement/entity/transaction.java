package com.example.transactionManagement.entity;


import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class transaction {

    @Id
    @GeneratedValue(generator = "transIDGenerator")
    @Column(name = "transactionid")
    private int transactionID;
    @Column(name = "senderphone")
    private long senderPhone;
    @Column(name = "receiverphone")
    private long receiverPhone;
    @Column(name = "amount")
    private int amount;
    @Column(name = "status")
    private String status;
    @Column(name = "remarks")
    private String remarks;

    public transaction(){}

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status;  }

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
