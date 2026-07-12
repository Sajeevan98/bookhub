package com.sajee.bookhub.common;

import java.time.LocalDateTime;

public record ErrorResponse(

        boolean success,
        int status,
        String message,
        LocalDateTime timestamp
) {
}
