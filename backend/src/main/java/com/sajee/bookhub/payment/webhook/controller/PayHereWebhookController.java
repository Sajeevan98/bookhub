package com.sajee.bookhub.payment.webhook.controller;

import com.sajee.bookhub.common.ApiEndpoints;
import com.sajee.bookhub.payment.webhook.dto.PayHereWebhookRequest;
import com.sajee.bookhub.payment.webhook.service.PayHereWebhookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.PAYMENTS + "/webhook")
@RequiredArgsConstructor
public class PayHereWebhookController {

    private final PayHereWebhookService webhookService;

    @PostMapping("/payhere")
    public ResponseEntity<String> receiveWebhook(@ModelAttribute PayHereWebhookRequest request) {

        webhookService.process(request);
        return ResponseEntity.ok("OK");
    }
}
