package com.sajee.bookhub.exception.business;

import com.sajee.bookhub.exception.BusinessException;

public class OrderAlreadyPaidException extends BusinessException {

    public OrderAlreadyPaidException(String message) {
        super(message);
    }
}
