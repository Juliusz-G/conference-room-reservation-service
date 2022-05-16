package com.sda.conferenceroomreservationservice.exception.handler;

import com.sda.conferenceroomreservationservice.exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();

        List<String> errorList = new ArrayList<>();
        result.getFieldErrors()
                .forEach((fieldError) -> errorList.add(
                        fieldError.getObjectName() + "."
                                + fieldError.getField() + " : "
                                + fieldError.getDefaultMessage() + " : rejected value ["
                                + fieldError.getRejectedValue() + "]")
                );
        result.getGlobalErrors()
                .forEach((fieldError) -> errorList.add(
                        fieldError.getObjectName() + " : "
                                + fieldError.getDefaultMessage())
                );

        return Error.builder()
                .timestamp(LocalDateTime.now())
                .httpCode(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.name())
                .errorMessage(exception.getMessage())
                .fieldErrors(errorList)
                .build();
    }
}
