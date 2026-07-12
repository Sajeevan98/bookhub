package com.sajee.bookhub.payment.dto.response;

import java.util.Map;

public record PaymentInitiateResponse(

        String checkoutUrl,

        Map<String, String> formFields
) {
}
