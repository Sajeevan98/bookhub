package com.sajee.bookhub.payment.webhook.dto;

public record PayHereWebhookRequest(

        String merchant_id,

        String order_id,

        String payment_id,

        String payhere_amount,

        String payhere_currency,

        String status_code,

        String md5sig,

        String method,

        String status_message,

        String custom_1,

        String custom_2
) {
}