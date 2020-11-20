/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlineBankingService.models;

import java.util.List;


public class Account {
    private String accountName;
    private String sortCode;
    private long id;
    private double currentBalance;
    private List<Transaction> transactions;

    
    
    public Account(){
        
    }

    public Account(String sortCode, long id, double currentBalance, List transactions,String accountName) {
        this.sortCode = sortCode;
        this.id = id;
        this.currentBalance = currentBalance;
        this.transactions = transactions;
        this.accountName = accountName;
    }
    
    //Get and set methods allow the values to be returned and changed
    
    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
    public void updateCurrentBalance(double money) {
        this.currentBalance += money;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
