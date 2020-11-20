/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlineBankingService.models;

import java.util.Date;


public class Transaction {
    
    private Date date;
    private String transactionType;
    private String description;
    private double postBalance;
    private double transactionAmount;
    private long transactionID;
    
    public Transaction() {
    }

    public Transaction(Date date, String description, double postBalance, double transactionAmount, long transactionID,String transactionType) {
        this.date = date;
        this.description = description;
        this.postBalance = postBalance;
        this.transactionAmount = transactionAmount;
        this.transactionID = transactionID;
        this.transactionType = transactionType;
    }
    
    //Get and set methods allow the values to be returned and changed

    public Date getDate() {
        return date;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPostBalance() {
        return postBalance;
    }

    public void setPostBalance(double postBalance) {
        this.postBalance = postBalance;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    
    
    
}
