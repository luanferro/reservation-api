package com.luanferro.reservation_api.adapters.in.security;

import com.luanferro.reservation_api.adapters.in.web.dto.ErrorResponseWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ErrorResponseWriter writer;

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        writer.write(response, 403, "Acesso negado");

    }
}
