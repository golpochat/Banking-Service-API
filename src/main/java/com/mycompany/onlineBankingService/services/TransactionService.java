/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlineBankingService.services;

import com.mycompany.onlineBankingService.databases.Database;
import com.mycompany.onlineBankingService.models.Account;
import com.mycompany.onlineBankingService.models.Transaction;
import com.mycompany.onlineBankingService.models.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class TransactionService {

    private List<Account> clist = new Database().getAccountsDB();
    private List<Transaction> slist = new Database().getTransactionsDB();
    private List<Customer> hlist = new Database().getCustomersDB();
    private long customerID;
    private long accountID;
    private Date date;

    public TransactionService(long customerID, long accountID) {
        this.accountID = accountID;
        this.customerID = customerID;
    }
    
    // Returns all transactions for an acconut
    public List<Transaction> getAllTransactionsByAccount() {
        int j = Math.toIntExact(customerID) - 1;
        int i = Math.toIntExact(accountID) - 1;
        return hlist.get(j).getAccounts().get(i).getTransactions();
    }
    
    // Checks to see if the account has a high enough balance
    public boolean hasEnoughBlance(double postBalance) {
        return postBalance <= hlist.get(Math.toIntExact(customerID) - 1).getAccounts().get(Math.toIntExact(accountID) - 1).getCurrentBalance();
    }
    
    // Makes a withdrawal transaction and returns the details
    public Transaction withdrawalTransaction(Transaction m) {
        double transactionAmount = m.getTransactionAmount();
        String message = "Debit";
        // Checks if the account has enough funds
        if (!hasEnoughBlance(transactionAmount)) {
            message = "Insufficient balance [" + transactionAmount + "] to withdraw.";
            transactionAmount = 0;
        }
        m.setTransactionAmount(-transactionAmount);
        m.setTransactionType(message);
        return createTransaction(customerID, accountID, m);
    }

    // Lodges the amount for external customer accounts
    public Transaction lodgementTransaction(long CustomerID, long AccountID, Transaction m) {
        m.setTransactionType("Credit");
        return createTransaction(CustomerID, AccountID, m);
    }
    
    // For lodges money for same customer accounts
    public Transaction lodgementTransaction(long otherAccountID, Transaction m) {
        m.setTransactionType("Credit");
        return createTransaction(customerID, otherAccountID, m);
    }
    
    // Lodges money from credit card
    public Transaction lodgementTransaction(Transaction m) {
        m.setTransactionType("Credit");
        return createTransaction(customerID, accountID, m);
    }

    // Calls a lodgement and withdraw methods to complete a transaction
    // For same customer accounts
    public Transaction transferTransaction(long otherAccountID, Transaction m) {
        Transaction r = new Transaction();
        r.setTransactionAmount(m.getTransactionAmount());
        r.setDescription(m.getDescription());
        r = withdrawalTransaction(r);
        
        // Checks if the account has enough funds
        if (hasEnoughBlance(m.getTransactionAmount())) {
            lodgementTransaction(otherAccountID, m);
        }
        return r;
    }
    
    // Calls a lodgement and withdraw methods to complete a transaction
    // For same external customer accounts
    public Transaction transferTransaction(long otherAccountID, long otherCustomerID, Transaction m) {
        Transaction r = new Transaction();
        r.setTransactionAmount(m.getTransactionAmount());
        r.setDescription(m.getDescription());
        r = withdrawalTransaction(r);
        
        // Checks if the account has enough funds
        if (hasEnoughBlance(m.getTransactionAmount())) {
            lodgementTransaction(otherCustomerID, otherAccountID, m);
        }
        return r;
    }
    
    // Creats a Transaction report for an account
    public Transaction createTransaction(long CustomerID, long AccountID, Transaction m) {
        List<Transaction> flist = new ArrayList();
        date = new Date();
        int j = Math.toIntExact(AccountID);
        int i = Math.toIntExact(CustomerID);
        
        // Sets the date the transaction was completed
        m.setDate(date);
        
        // Gets the id for the transaction
        m.setTransactionID(hlist.get(i - 1).getAccounts().get(j - 1).getTransactions().size() + 1);
        
        // Updates the balance of the account
        hlist.get(i - 1).getAccounts().get(j - 1).updateCurrentBalance(m.getTransactionAmount());
        
        // Sets the post balance amount for the transaction record
        m.setPostBalance(hlist.get(i - 1).getAccounts().get(j - 1).getCurrentBalance());
        for (Transaction f : hlist.get(i - 1).getAccounts().get(j - 1).getTransactions()) {
            flist.add(f);
        }
        flist.add(m);
        hlist.get(i - 1).getAccounts().get(j - 1).setTransactions(flist);
        return m;
    }

}
