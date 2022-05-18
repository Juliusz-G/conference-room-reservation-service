package com.sda.conferenceroomreservationservice.exception.handler;

import com.sda.conferenceroomreservationservice.exception.Error;
import com.sda.conferenceroomreservationservice.exception.type.reservation.ReservationCreationException;
import com.sda.conferenceroomreservationservice.exception.type.reservation.ReservationNotFoundException;
import com.sda.conferenceroomreservationservice.exception.type.reservation.ReservationPeriodNotFree;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;

@RestControllerAdvice
public class ReservationExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReservationNotFoundException.class)
    public Error handleReservationNotFoundException(final ReservationNotFoundException exception) {
        return Error.builder()
                .timestamp(LocalDateTime.now())
                .httpCode(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.name())
                .errorMessage(exception.getMessage())
                .fieldErrors(Collections.emptyList())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReservationPeriodNotFree.class)
    public Error handleReservationPeriodNotFree(final ReservationPeriodNotFree exception) {
        return Error.builder()
                .timestamp(LocalDateTime.now())
                .httpCode(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .errorMessage(exception.getMessage())
                .fieldErrors(Collections.emptyList())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReservationCreationException.class)
    public Error handleReservationCreationException(final ReservationCreationException exception) {
        return Error.builder()
                .timestamp(LocalDateTime.now())
                .httpCode(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .errorMessage(exception.getMessage())
                .fieldErrors(Collections.emptyList())
                .build();
    }
}
