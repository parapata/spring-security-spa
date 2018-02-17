package com.example.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.domain.Users;

public class LoginUser implements UserDetails {

    private String userName;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private List<GrantedAuthority> grantedAuthoritys;

    private String email;
    private String publicName;

    public LoginUser(Users users) {

        this.userName = users.getUserId();
        this.password = users.getPassword();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.grantedAuthoritys = new ArrayList<GrantedAuthority>();

        this.email = users.getEmail();
        this.publicName = String.format("%s\\u3000%s", users.getFirstName(), users.getLastName());
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthoritys;
    }

    public String getEmail() {
        return email;
    }

    public String getPublicName() {
        return publicName;
    }

}
