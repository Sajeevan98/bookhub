package com.sajee.bookhub.payment.webhook.verify;

import com.sajee.bookhub.payment.webhook.dto.PayHereWebhookRequest;

public interface SignatureVerifier {

    boolean verify(PayHereWebhookRequest request);
}
