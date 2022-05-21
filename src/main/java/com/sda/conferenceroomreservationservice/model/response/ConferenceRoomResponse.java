package com.sda.conferenceroomreservationservice.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ConferenceRoomResponse {

    private final Long id;
    private final String name;
    private final String identifier;
    private final Integer level;
    private final Boolean availability;
    private final Integer numberOfStandingPlaces;
    private final Integer numberOfSittingPlaces;
    private final String  organisationName;
}
