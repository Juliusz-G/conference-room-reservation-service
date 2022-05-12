package com.sda.conferenceroomreservationservice.exception.handler;

import com.sda.conferenceroomreservationservice.exception.type.ConferenceRoomNotFoundException;
import com.sda.conferenceroomreservationservice.exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ConferenceRoomExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ConferenceRoomNotFoundException.class)
    public Error handleConferenceRoomNotFoundException(final ConferenceRoomNotFoundException exception) {
        return new Error(exception.getMessage());
    }
}
