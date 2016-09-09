package com.ayadykin.blackjack.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.utils.Constants;

/**
 * Created by Andrey Yadykin on 15 бер. 2016 р.
 */

@Entity
@Table(name = "users")
@NamedQuery(name = Constants.FIND_BY_EMAIL, query = "SELECT u FROM User u where u.email = :email")
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne(mappedBy = "user")
    private Account account;
    private String email;
    private String password;
    private String name;

    @OneToOne
    private Role role;
    @Transient
    private Player player;

    @Transient
    private List<GrantedAuthority> authorities = null;

    public User() {
        // player = new Player(id, account.getBalance());
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
