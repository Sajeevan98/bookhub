package com.sajee.bookhub.exception.business;

import com.sajee.bookhub.exception.BusinessException;

public class InvalidPaymentSignatureException extends BusinessException {
    public InvalidPaymentSignatureException(String message) {
        super(message);
    }
}
