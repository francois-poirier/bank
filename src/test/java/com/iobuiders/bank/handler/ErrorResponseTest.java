package com.iobuiders.bank.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ErrorResponseTest {

    private static final String DEFAULT_MESSAGE = "Default message";
    private static final Integer DEFAULT_STATUS = 500;

    @Test
    void givenValidDataWhenBuildThenReturnErrorResponse() {
        final ErrorResponse response = ErrorResponse.builder().status(DEFAULT_STATUS)
                .message(DEFAULT_MESSAGE).build();
        assertEquals(DEFAULT_STATUS, response.getStatus());
        assertEquals(DEFAULT_MESSAGE, response.getMessage());
    }

    @Test
    void shouldBeEqualsAndSymmetric() {
        final ErrorResponse response = ErrorResponse.builder().status(DEFAULT_STATUS)
                .message(DEFAULT_MESSAGE).build();
        final ErrorResponse otherResponse = ErrorResponse.builder().status(DEFAULT_STATUS)
                .message(DEFAULT_MESSAGE).build();

        assertTrue(response.equals(otherResponse) && otherResponse.equals(response));
        assertEquals(response.hashCode(),otherResponse.hashCode());
    }
}