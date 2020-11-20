/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlineBankingService.services;

import com.mycompany.onlineBankingService.databases.Database;
import com.mycompany.onlineBankingService.models.Account;
import com.mycompany.onlineBankingService.models.Customer;
import com.mycompany.onlineBankingService.models.Transaction;
import java.util.ArrayList;
import java.util.List;



public class AccountService {
    
    private List<Customer> clist = new Database().getCustomersDB();
    private List<Account> slist;
    private int customerID;
    
    // Sets the Account list and reduces CustomerID as it starts at 0
    public AccountService(long customerID){
        this.customerID = Math.toIntExact(customerID) - 1;
        slist = clist.get(this.customerID).getAccounts();
    }
    
    // Returns a list of all account for a customer
    public List<Account> getAllAccountsByCustomer() {
        return slist;
    }
    
    // Returns an account of a given ID
    public Account getAccountByID(long accountNumber) {
        int i = Math.toIntExact(accountNumber);
        return slist.get(i-1);
    }
    
    // Returns the current balance for an account
    public double getAccountBalanceByID(long accountNumber) {
        int i = Math.toIntExact(accountNumber);
        return slist.get(i-1).getCurrentBalance();
    }
    
    // Creates an account
    public Account createAccount(Account m) {
        List<Account> flist = new ArrayList();
        
        for (Account f: slist){
            flist.add(f);
        }
        // Sets the defaults for a new account
        m.setSortCode("30-23-23");
        m.setCurrentBalance(0.0);
        // Sets the ID of the new account
        m.setId(slist.size() + 1);
        List<Transaction> transactionList = new  ArrayList();
        m.setTransactions(transactionList);
	flist.add(m);
        clist.get(customerID).setAccounts(flist);
	return m;
    }
    
}
