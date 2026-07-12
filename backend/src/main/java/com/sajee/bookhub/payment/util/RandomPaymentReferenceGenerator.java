package com.sajee.bookhub.payment.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RandomPaymentReferenceGenerator implements PaymentReferenceGenerator {

    @Override
    public String generate() {

        return "PAY-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();
    }
}
