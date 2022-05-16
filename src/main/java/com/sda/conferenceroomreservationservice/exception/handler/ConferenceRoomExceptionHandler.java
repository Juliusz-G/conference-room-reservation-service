package com.sda.conferenceroomreservationservice.exception.handler;

import com.sda.conferenceroomreservationservice.exception.Error;
import com.sda.conferenceroomreservationservice.exception.type.conferenceroom.ConferenceRoomAlreadyExists;
import com.sda.conferenceroomreservationservice.exception.type.conferenceroom.ConferenceRoomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;

@RestControllerAdvice
public class ConferenceRoomExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ConferenceRoomNotFoundException.class)
    public Error handleConferenceRoomNotFoundException(final ConferenceRoomNotFoundException exception) {
        return Error.builder()
                .timestamp(LocalDateTime.now())
                .httpCode(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.name())
                .errorMessage(exception.getMessage())
                .fieldErrors(Collections.emptyList())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(ConferenceRoomAlreadyExists.class)
    public Error handleConferenceRoomAlreadyExists(final ConferenceRoomAlreadyExists exception) {
        return Error.builder()
                .timestamp(LocalDateTime.now())
                .httpCode(HttpStatus.NOT_ACCEPTABLE.value())
                .error(HttpStatus.NOT_ACCEPTABLE.name())
                .errorMessage(exception.getMessage())
                .fieldErrors(Collections.emptyList())
                .build();
    }
}
