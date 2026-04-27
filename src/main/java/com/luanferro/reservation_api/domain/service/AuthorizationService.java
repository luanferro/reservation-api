package com.luanferro.reservation_api.domain.service;

import com.luanferro.reservation_api.adapters.in.security.UserDetailsImpl;
import com.luanferro.reservation_api.domain.port.out.UserRepository;
import org.hibernate.validator.internal.metadata.PredefinedScopeBeanMetaDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));
    }
}
