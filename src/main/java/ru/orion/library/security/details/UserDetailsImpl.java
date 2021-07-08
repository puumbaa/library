package ru.orion.library.security.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.orion.library.enums.AccountStatus;
import ru.orion.library.models.Account;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private final Account account;

    public UserDetailsImpl(Account account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRole = account.getRole().name();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole);
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return account.getHashPassword();
    }

    @Override
    public String getUsername() {
        return account.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !account.getStatus().equals(AccountStatus.BANNED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return account.getStatus().equals(AccountStatus.ACTIVE);
    }

    public Account getAccount() {
        return account;
    }
}
