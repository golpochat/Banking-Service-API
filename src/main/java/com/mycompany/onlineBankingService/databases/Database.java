/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlineBankingService.databases;

import com.mycompany.onlineBankingService.models.Account;
import com.mycompany.onlineBankingService.models.Customer;
import com.mycompany.onlineBankingService.models.Transaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Database {
    public static List<Customer> customerDB = new ArrayList<>();
    public static List<Account> accountDB = new ArrayList<>();
    public static List<Transaction> transactionDB = new ArrayList<>();
    public static boolean init = true;
    private Date firstDate = new Date();
     
    // Seeds the data base with the values given
    public Database () {
      if (init) {
          
        Transaction t1 = new Transaction (firstDate, "Rent", 5000.00 , 200.00, 1, "Credit");
        Transaction t2 = new Transaction (firstDate, "Wages", 4700.00 , 300.00, 2, "Debit");
        
        
        transactionDB.add(t1);
        transactionDB.add(t2);
        
        Account s1 = new Account ("30-23-23",1, 4000.00, transactionDB, "currentSaver");  
        Account s2 = new Account ("30-23-23",2, 5000.00, transactionDB, "currentAccount");
        
        accountDB.add(s1);
        accountDB.add(s2);
        
        Customer c1 = new Customer(1,"gearoid", "dublin", "emailaddress", "password", "gearoid", accountDB);
              
        customerDB.add(c1);
        
        init = false;
     }
    }
    // Following return lists of the each database
    public static List<Customer> getCustomersDB() {
        return customerDB;
    }
    
    public static List<Account> getAccountsDB() {
        return accountDB;
    }
    public static List<Transaction> getTransactionsDB() {
        return transactionDB;
    }
}
