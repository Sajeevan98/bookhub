package com.sajee.bookhub.payment.gateway;

import com.sajee.bookhub.payment.enums.PaymentGatewayType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentGatewayFactory {

    private final List<PaymentGateway> gateways;

    public PaymentGateway getGateway(PaymentGatewayType gatewayType) {

        return gateways.stream()
                .filter(gateway -> gateway.getGateway() == gatewayType)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Unsupported payment gateway: " + gatewayType
                        )
                );
    }
}
