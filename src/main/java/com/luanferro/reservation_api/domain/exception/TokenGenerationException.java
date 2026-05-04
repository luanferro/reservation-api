package com.luanferro.reservation_api.domain.exception;

public class TokenGenerationException extends RuntimeException {
    public TokenGenerationException(Throwable cause) {
        super(cause);
    }
}
