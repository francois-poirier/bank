package com.iobuiders.bank.handler;

import java.io.IOException;

import com.iobuiders.bank.exception.BusinessException;
import com.iobuiders.bank.exception.CommunicationException;
import com.iobuiders.bank.exception.EntityNotFoundException;
import com.iobuiders.bank.exception.NotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler
{
    @ExceptionHandler(value = { IOException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse badRequest(Exception ex)
    {
        return ErrorResponse.builder()
                .status(400)
                .message("Bad Request")
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse validationError(Exception ex) {
        return ErrorResponse.builder()
                .status(400)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(value = { EntityNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse unKnownException(Exception ex)
    {
        return ErrorResponse.builder()
                .status(404)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(value = { BusinessException.class })
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse businessException(Exception ex)
    {
        return ErrorResponse.builder()
                .status(422)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(value = { NotAvailableException.class })
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorResponse notAvailableException(Exception ex)
    {
        return ErrorResponse.builder()
                .status(503)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(value = { CommunicationException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse communicationException(Exception ex)
    {
        return ErrorResponse.builder()
                .status(500)
                .message(ex.getMessage())
                .build();
    }

}