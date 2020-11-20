package com.mycompany.onlineBankingService.resources;

import com.mycompany.onlineBankingService.models.Account;
import com.mycompany.onlineBankingService.models.Transaction;
import com.mycompany.onlineBankingService.services.TransactionService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


@Path("/transactions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransactionResource {
    private TransactionService TransactionService;
    private long customerID;
    private long accountID;
    
    public TransactionResource(long customerID, long accountID){
        this.accountID = accountID;
        this.customerID = customerID;
        TransactionService = new TransactionService(customerID, accountID);
    }
    
    // Gets a list of all transactions for an account
    @GET
    public List<Transaction> getTransactionsforAccount() {
	return TransactionService.getAllTransactionsByAccount();
    }
    
    // Lodges the amount passed
    @POST
    @Path("/lodge")
    public Transaction postLodgeTransaction(Transaction m) {
        m.setTransactionType("Credit");
        return TransactionService.lodgementTransaction(m);
    }
    
    // Withdraws the amount passed
    @POST
    @Path("/withdraw")
    public Transaction postWithdrawTransaction(Transaction m) {
        return TransactionService.withdrawalTransaction(m);
    }
    
    // Takes in paramters for account to transfer to and the customer
    // Completes and returns transaction details
    @POST
    @Path("/transfer/{otherAccountID}")
    public Transaction postTransferExternalTransaction(@PathParam("otherAccountID") int otherTranscationNumber,@QueryParam("otherCustomerID") int otherCustomerID,  Transaction m){
        // Checks if the customer is external or the same account
        if ((otherCustomerID != 0)) {
            return TransactionService.transferTransaction(otherTranscationNumber,otherCustomerID, m);
        }
        return TransactionService.transferTransaction(otherTranscationNumber, m);
        
    }
    
}
