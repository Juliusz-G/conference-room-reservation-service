package com.sda.conferenceroomreservationservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class Error {
    private final LocalDateTime timestamp;
    private final String message;
    private final int httpCode;
}
