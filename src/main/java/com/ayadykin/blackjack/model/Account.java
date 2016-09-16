package com.ayadykin.blackjack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import com.ayadykin.blackjack.utils.Constants;

/**
 * Created by Andrey Yadykin on 22.02.2016 ð.
 */

@Entity
@NamedQuery(name = Constants.FIND_BY_PURSE, query = "SELECT a FROM Account a where a.purseId = :purseId")
public class Account {

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private double balance;
    @Column(nullable = false)
    private long purseId;
    @OneToOne
    private User user;

    public Account() {

    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Account [id=" + id + ", balance=" + balance + ", purseId=" + purseId + "]";
    }

}
