package com.sda.conferenceroomreservationservice.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class ReservationDto {

    private final Long id;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final ConferenceRoomDto conferenceRoom;
}
