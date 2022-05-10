package com.sda.conferenceroomreservationservice.model.dto;

import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ReservationDto {

    private final Long id;
    private final String name;
    private final Timestamp start;
    private final Timestamp end;
    private final ConferenceRoom conferenceRoom;
    private final Organisation organisation;
}
