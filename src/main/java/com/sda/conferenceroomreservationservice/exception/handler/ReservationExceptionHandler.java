package com.sda.conferenceroomreservationservice.exception.handler;

import com.sda.conferenceroomreservationservice.exception.Error;
import com.sda.conferenceroomreservationservice.exception.type.reservation.ReservationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ReservationExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReservationNotFoundException.class)
    public Error handleReservationNotFoundException(final ReservationNotFoundException exception) {
        return Error.builder()
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .httpCode(HttpStatus.NOT_FOUND.value())
                .build();
    }
}
