package com.sajee.bookhub.payment.webhook.service;

import com.sajee.bookhub.payment.webhook.dto.PayHereWebhookRequest;

public interface PayHereWebhookService {

    void process(PayHereWebhookRequest request);
}
