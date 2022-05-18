package com.sda.conferenceroomreservationservice.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class ConferenceRoomDto {

    private final Long id;
    private final String name;
    private final String identifier;
    private final Integer level;
    @Builder.Default
    private final Boolean availability = true;
    private final Integer numberOfStandingPlaces;
    private final Integer numberOfSittingPlaces;
    private final OrganisationDto organisation;
    @Builder.Default
    private final List<ReservationDto> reservationList = new ArrayList<>();
}
