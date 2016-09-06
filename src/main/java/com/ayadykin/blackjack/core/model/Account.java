package com.ayadykin.blackjack.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * Created by Andrey Yadykin on 22.02.2016 ð.
 */

@Entity
@NamedQuery(name = Account.FIND_BY_PURSE, query = "SELECT a FROM Account a where a.purseId = :purseId")
public class Account {
    
    public static final String FIND_BY_PURSE = "Account.findByPurse";
    
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private double account;
    @Column(nullable = false)
    private long purseId;

    public Account(){
        
    }
            
    public Account(double account, long purseId) {
        this.account = account;
        this.purseId = purseId;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPurseId() {
        return purseId;
    }

    public void setPurseId(long purseId) {
        this.purseId = purseId;
    }

}
