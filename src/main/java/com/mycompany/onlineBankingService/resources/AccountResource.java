/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlineBankingService.resources;

import com.mycompany.onlineBankingService.models.Account;
import com.mycompany.onlineBankingService.services.AccountService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {
    
    
    private long customerID;
    private AccountService AccountService;
    public AccountResource(long customerID){
        this.customerID = customerID;
        AccountService = new AccountService(customerID);
    }
    
    // Returns a list of all the accounts for a customer
    @GET
    public List<Account> getAllAccountsByCustomer() {
	return AccountService.getAllAccountsByCustomer();
    }
    
    // Creates a new account
    @POST
    public Account postAccount(Account m) {
	return AccountService.createAccount(m);
    }
    
    //Returns the account of a given ID
    @GET
    @Path("/{accountID}")
    public Account getAccount(@PathParam("accountID") int accountNumber) {
	return AccountService.getAccountByID(accountNumber);
    }
    
    // Returns the balance of an account
    @GET
    @Path("/{accountID}/balance")
    public String getAccountBalance(@PathParam("accountID") int accountNumber) {
	return "" + AccountService.getAccountBalanceByID(accountNumber);
    }
    
    // Connects Account Resources to Transaction Resources
    @Path("/{accountID}/transactions")
    public TransactionResource getTransactionsResource(@PathParam("accountID") int accountID) {
	return new TransactionResource(customerID, accountID);
    }
}
