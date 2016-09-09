package com.ayadykin.blackjack.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Yadykin Andrii Sep 9, 2016
 *
 */

@Entity
public class Role implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }

}
