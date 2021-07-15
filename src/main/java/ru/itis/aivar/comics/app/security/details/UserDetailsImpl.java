package ru.itis.aivar.comics.app.security.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.aivar.comics.app.models.Role;
import ru.itis.aivar.comics.app.models.User;

import java.util.Collection;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

    private String email;
    private String hashPassword;
    private boolean isActive;
    private Set<Role> roles;

    public UserDetailsImpl(User user) {
        email = user.getEmail();
        hashPassword = user.getHashPassword();
        isActive = user.isActive();
        roles = user.getRoles();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return hashPassword;
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
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
