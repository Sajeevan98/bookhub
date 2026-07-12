package com.sajee.bookhub.order.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SimpleOrderNumberGenerator implements OrderNumberGenerator{

    @Override
    public String generate() {

        return "ORD-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();
    }
}
