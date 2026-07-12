package com.sajee.bookhub.exception.business;

import com.sajee.bookhub.exception.BusinessException;

public class PaymentAlreadyPendingException extends BusinessException {

    public PaymentAlreadyPendingException(String message) {
        super(message);
    }
}
