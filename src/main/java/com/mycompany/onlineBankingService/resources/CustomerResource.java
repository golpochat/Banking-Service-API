/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlineBankingService.resources;

import com.mycompany.onlineBankingService.models.Customer;
import com.mycompany.onlineBankingService.services.CustomerService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.QueryParam;


// Access index page
@Path("/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
    
    CustomerService customerService = new CustomerService();
    // Get customers by ID
    @GET
    @Path("/{customerId}")
    public Customer getCustomer(@PathParam("customerId") int id) {
        return customerService.getCustomer(id);
    }
    
    // Create new customer
    @POST
    public Customer postCustomer(Customer m) {
	return customerService.createCustomer(m);
    }
    // Use QueryParams to get a customer by username
    @GET
    public Customer getFilteredCustomers(@QueryParam("username") String userName, @QueryParam("password") String password) {
        
        if ((userName != null) && (password != null)) {
                     return customerService.getSearchCustomers(userName, password);
        } else if (userName != null){
            return customerService.getSearchCustomers(userName);
        }
        return customerService.getDefaultCustomers();
    }
    
    // To get all customers
    @GET
    @Path("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    
    // Connects Customer Resources to Account Resources
    @Path("/{customerID}/accounts")
    public AccountResource getAccountsResource(@PathParam("customerID") int customerID) {
	return new AccountResource(customerID);
    }

}
