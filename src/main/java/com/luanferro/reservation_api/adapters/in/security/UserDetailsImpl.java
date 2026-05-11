package com.luanferro.reservation_api.adapters.in.security;

import com.luanferro.reservation_api.domain.enums.UserRole;
import com.luanferro.reservation_api.domain.model.User;
import com.luanferro.reservation_api.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.user.getRole() == UserRole.ADMIN) return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.user.getSenha();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }
}
