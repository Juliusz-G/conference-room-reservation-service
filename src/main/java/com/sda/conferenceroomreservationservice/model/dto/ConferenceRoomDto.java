package com.sda.conferenceroomreservationservice.model.dto;

import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import com.sda.conferenceroomreservationservice.model.entity.Reservation;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ConferenceRoomDto {

    private final Long id;
    private final String name;
    private final String identifier;
    private final Integer level;
    private final Boolean availability;
    private final Integer numberOfStandingPlaces;
    private final Integer numberOfSittingPlaces;
    private final List<Reservation> reservationList;
    private final Organisation organisation;
}
