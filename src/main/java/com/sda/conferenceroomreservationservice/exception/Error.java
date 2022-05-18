package com.sda.conferenceroomreservationservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class Error {

    private final LocalDateTime timestamp;
    private final int httpCode;
    private final String error;
    private final String errorMessage;
    private final List<String> fieldErrors;
}
