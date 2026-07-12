package com.sajee.bookhub.payment.security;

import com.sajee.bookhub.payment.entity.Payment;

public interface HashGenerator {

    String generateHash(Payment payment);
}
