package com.example.userManagement.entity;

import javax.persistence.*;

@Entity
@Table(name= "userdata")

public class userData {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String userName;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "emailid")
    private String emailID;
    @Column(name = "mobilenumber")
    private long mobileNumber;
    @Column(name = "address1")
    private String address1;
    @Column(name = "address2")
    private String address2;

    public userData(int id, String userName, String firstName, String lastName, String emailID, long mobileNumber, String address1, String address2) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailID = emailID;
        this.mobileNumber = mobileNumber;
        this.address1 = address1;
        this.address2 = address2;
    }

       public userData(){}      // default constructor


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
}
