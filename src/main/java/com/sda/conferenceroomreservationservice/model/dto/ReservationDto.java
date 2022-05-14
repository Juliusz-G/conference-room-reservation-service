package com.sda.conferenceroomreservationservice.model.dto;

import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ReservationDto {

    private final Long id;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final ConferenceRoom conferenceRoom;
}
