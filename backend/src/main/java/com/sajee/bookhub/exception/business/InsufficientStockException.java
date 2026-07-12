package com.sajee.bookhub.exception.business;

import com.sajee.bookhub.exception.BusinessException;

public class InsufficientStockException extends BusinessException {

    public InsufficientStockException(String title, int requested, int available) {

        super(String.format(
                "Insufficient stock for '%s'. Requested: %d, Available: %d",
                title,
                requested,
                available
        ));
    }
}