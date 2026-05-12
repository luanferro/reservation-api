package com.luanferro.reservation_api.adapters.in.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityContext implements SecurityContext{
    @Override
    public String getCurrentUserSubject() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("not authenticated user in context");
        }

        return authentication.getName();
    }
}
