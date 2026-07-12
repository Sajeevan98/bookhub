package com.sajee.bookhub.common;

import java.time.LocalDateTime;

public record SuccessResponse<T>(

        boolean success,
        String message,
        T data,
        LocalDateTime timestamp
) {

    public static <T> SuccessResponse<T> success(String message, T data) {
        return new SuccessResponse<>(
                true,
                message,
                data,
                LocalDateTime.now()
        );
    }

    public static <T> SuccessResponse<T> success(T data) {
        return success("Success", data);
    }
}
