package com.sajee.bookhub.book.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IsbnGeneratorImp implements IsbnGenerator {

    @Override
    public String generate() {

        return "Isbn-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();
    }
}
