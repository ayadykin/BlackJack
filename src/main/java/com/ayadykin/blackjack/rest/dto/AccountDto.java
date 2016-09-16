package com.ayadykin.blackjack.rest.dto;

import java.io.Serializable;

/**
 * Created by Yadykin Andrii Sep 5, 2016
 *
 */

public class AccountDto implements Serializable {

    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String locale;

    public AccountDto() {

    }

    public AccountDto(long id, String email, String firstName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public String toString() {
        return "AccountDto [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", locale=" + locale
                + "]";
    }

}
