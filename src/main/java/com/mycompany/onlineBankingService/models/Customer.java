/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlineBankingService.models;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Customer {
    
    
    private String name;
    private String correspondenceAddress;
    private String email;
    private String password;
    private String userName;
    private List<Account> accounts;
    private long id;
    
    public Customer() {
    }
    
    
    public Customer(long id, String name, String correspondenceAddress, String email, String password, String userName, List accounts) {
        this.id = id;
        this.correspondenceAddress = correspondenceAddress;
        this.name = name;
        this.email = email;
        this.accounts = accounts;
        this.userName = userName;
        this.password = password;
    }
    
    //Get and set methods allow the values to be returned and changed

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCorrespondenceAddress() {
        return correspondenceAddress;
    }

    public void setCorrespondenceAddress(String correspondenceAddress) {
        this.correspondenceAddress = correspondenceAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
