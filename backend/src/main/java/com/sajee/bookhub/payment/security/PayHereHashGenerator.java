package com.sajee.bookhub.payment.security;

import com.sajee.bookhub.payment.config.PayHereProperties;
import com.sajee.bookhub.payment.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
@RequiredArgsConstructor
public class PayHereHashGenerator implements HashGenerator {

    private final PayHereProperties properties;

    @Override
    public String generateHash(Payment payment) {

        String merchantId = properties.getMerchantId();

        String orderId = payment.getPaymentReference();

        String amount = payment.getAmount()
                .setScale(2, RoundingMode.HALF_UP)
                .toPlainString();

        String currency = properties.getCurrency();

        String hashedSecret = md5(properties.getMerchantSecret());

        return md5(
                merchantId +
                        orderId +
                        amount +
                        currency +
                        hashedSecret
        );
    }

    // this returns uppercase hexadecimal
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

            throw new RuntimeException("Failed to generate MD5 hash.", ex);
        }
    }
}
