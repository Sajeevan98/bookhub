package com.sajee.bookhub.payment.gateway.payhere;

import com.sajee.bookhub.payment.config.PayHereProperties;
import com.sajee.bookhub.payment.dto.response.PaymentInitiateResponse;
import com.sajee.bookhub.payment.entity.Payment;
import com.sajee.bookhub.payment.enums.PaymentGatewayType;
import com.sajee.bookhub.payment.gateway.PaymentGateway;
import com.sajee.bookhub.payment.security.HashGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PayHereGateway implements PaymentGateway {

    private final PayHereProperties payHereProperties;
    private final HashGenerator hashGenerator;

    @Override
    public PaymentGatewayType getGateway() {
        return PaymentGatewayType.PAYHERE;
    }

    @Override
    public PaymentInitiateResponse initiatePayment(Payment payment) {

        Map<String, String> fields = new LinkedHashMap<>();

        fields.put("merchant_id", payHereProperties.getMerchantId());
        fields.put("return_url", payHereProperties.getReturnUrl());
        fields.put("cancel_url", payHereProperties.getCancelUrl());
        fields.put("notify_url", payHereProperties.getNotifyUrl());
        fields.put("order_id", payment.getPaymentReference());
        fields.put("items", payment.getOrder().getOrderNumber());
        fields.put("currency", payHereProperties.getCurrency());
        fields.put("amount", payment.getAmount().setScale(2).toPlainString());
        fields.put("first_name", payment.getOrder().getCustomerName());
        fields.put("email", payment.getOrder().getCustomerEmail());
        fields.put("hash", hashGenerator.generateHash(payment));
        fields.put("last_name", "Guest");
        fields.put("phone", "0771234567");
        fields.put("address", "Gale Face");
        fields.put("city", "Colombo");
        fields.put("country", "Sri Lanka");

        return new PaymentInitiateResponse(payHereProperties.getCheckoutUrl(), fields);
    }
}

