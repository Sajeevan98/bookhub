package com.sajee.bookhub.exception.business;

import com.sajee.bookhub.exception.BusinessException;

public class PaymentExpiredException extends BusinessException {
    public PaymentExpiredException(String message) {
        super(message);
    }
}
