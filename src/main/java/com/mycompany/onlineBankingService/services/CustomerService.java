/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlineBankingService.services;

import com.mycompany.onlineBankingService.databases.Database;
import com.mycompany.onlineBankingService.models.Customer;
import com.mycompany.onlineBankingService.models.Account;
import java.util.ArrayList;
import java.util.List;


public class CustomerService {
          
    private List<Customer> list = new Database().getCustomersDB();
    
    
    // Returns a list of all customers
    public List<Customer> getAllCustomers() {
        return list;
    }
    
    // Returns default customer
    public Customer getDefaultCustomers() {
        Customer matcheslist = new Customer();
        return matcheslist;
    }
    
    // Searches to see if the userName and password are correct
    public Customer getSearchCustomers(String userName, String password) {
       Customer matcheslist = new Customer();
        
        for (Customer q: getAllCustomers()) {
            if ((userName == null || q.getUserName().equals(userName)) 
                   && (password == null || q.getPassword().equals(password))) {
               return q;
            }
        }
        return matcheslist;
    }
    
    // Searches to see if a username already exists in the database
    public Customer getSearchCustomers(String userName) {
       Customer matcheslist = new Customer();
        
        for (Customer q: getAllCustomers()) {
            if (userName == null || q.getUserName().equals(userName)) {
               return q;
            }
        }
        return matcheslist;
    }
    
    // Returns a customer of a certain ID
    public Customer getCustomer(long id) {
        int i = Math.toIntExact(id);
        return list.get(i-1);
    }
    
    // Creates a new customer
    public Customer createCustomer(Customer m) {
        List<Account> accounts = new ArrayList();
        // Sets ID and blank accounts
	m.setId(list.size() + 1);
        m.setAccounts(accounts);
	list.add(m);
	return m;
    }
    
    
}
