package com.sda.conferenceroomreservationservice.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class ReservationResponse {

    private final Long id;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final String conferenceRoomName;
    private final String organisationName;
}
