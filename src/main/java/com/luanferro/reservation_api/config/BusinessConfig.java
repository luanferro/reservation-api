package com.luanferro.reservation_api.config;

import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;

@Configuration
public class BusinessConfig {
    public static final int RESERVATION_DURATION_HOURS = 3;
    public static final LocalTime RESTAURANT_OPEN = LocalTime.of(18, 0);
    public static final LocalTime RESTAURANT_CLOSED = LocalTime.of(23, 59);
}
