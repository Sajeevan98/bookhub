package com.sajee.bookhub.payment.webhook.verify;

import com.sajee.bookhub.payment.config.PayHereProperties;
import com.sajee.bookhub.payment.webhook.dto.PayHereWebhookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
@RequiredArgsConstructor
public class PayHereSignatureVerifier implements SignatureVerifier {

    private final PayHereProperties payHereProperties;

    @Override
    public boolean verify(PayHereWebhookRequest request) {

        String merchantSecretHash = md5(payHereProperties.getMerchantSecret());

        String localSignature = md5(
                request.merchant_id()
                        + request.order_id()
                        + request.payhere_amount()
                        + request.payhere_currency()
                        + request.status_code()
                        + merchantSecretHash
        );

        return localSignature.equalsIgnoreCase(request.md5sig());
    }

    private String md5(String input) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();

            for (byte b : digest) {
                sb.append(String.format("%02X", b));
            }

            return sb.toString();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
