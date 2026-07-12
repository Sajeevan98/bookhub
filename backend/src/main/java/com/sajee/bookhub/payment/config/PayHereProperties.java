package com.sajee.bookhub.payment.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "payhere")
public class PayHereProperties {

    /**
     * Merchant ID provided by PayHere.
     */
    private String merchantId;

    /**
     * Merchant Secret provided by PayHere.
     */
    private String merchantSecret;

    /**
     * Sandbox checkout URL.
     */
    private String checkoutUrl;

    /**
     * LKR
     */
    private String currency;

    /**
     * React Success URL
     */
    private String returnUrl;

    /**
     * React Cancel URL
     */
    private String cancelUrl;

    /**
     * Backend webhook URL
     */
    private String notifyUrl;
}